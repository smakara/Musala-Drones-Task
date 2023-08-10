package com.musala.drone.task.serviceImpl;

import java.security.cert.PKIXRevocationChecker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.drone.task.dto.APIResponse;
import com.musala.drone.task.dto.DroneBatteryLevelResponse;
import com.musala.drone.task.dto.DroneRegisterRequest;
import com.musala.drone.task.dto.LoadDroneRequest;
import com.musala.drone.task.dto.LoadedMedication;
import com.musala.drone.task.dto.MedicationRequest;
import com.musala.drone.task.enums.Model;
import com.musala.drone.task.model.Drone;
import com.musala.drone.task.model.Medication;
import com.musala.drone.task.repository.DroneRepository;
import com.musala.drone.task.repository.MedicationRepository;
import com.musala.drone.task.service.APIResponseService;
import com.musala.drone.task.service.DroneService;
import com.musala.drone.task.utils.Constants;
import com.musala.drone.task.utils.knapsack.ZeroOneKnapsackEngine;
import com.musala.drone.task.utils.knapsack.knapSackObject;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shepherd
 *
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DroneServiceImpl  implements DroneService{
	
	
	public final DroneRepository droneRepository;
	public final  APIResponseService apiResponse; 
	public final MedicationRepository medicationRepository;

	
	
	@Override
	
	public <T> APIResponse<T> register(DroneRegisterRequest request) {
		
		return (doesDroneSerialExists(request)) ?  (APIResponse<T>) ( apiResponse.buildAPIResponse(Constants.STATUS_DESC_DRONE_EXITS.concat(request.getSerialNumber()), Constants.STATUS_CODE_DRONE_EXITS)) :((APIResponse<T>) createDrone (request)) ;
	
		
		
	}



	private APIResponse createDrone(DroneRegisterRequest request) {
		Drone drone = new Drone() ;
		drone.setCapacity(request.getCapacity());
		drone.setModel(request.getModel().name());
		drone.setSerialNumber(request.getSerialNumber());
		drone.setState(Constants.IDLE);// default intitial weight is IDLE
		drone.setWeight(request.getWeight());
		Drone savedDrone  = droneRepository.save(drone) ;
		log.info("savedDrone : {}" ,(savedDrone));
		return apiResponse.buildAPIResponse(savedDrone, Constants.STATUS_CODE_SUCCESS);
	}
	
	private Boolean doesDroneSerialExists(DroneRegisterRequest request) {
		
		return droneRepository.findById(request.getSerialNumber()).isPresent() ;
	}



	@Override
	public <T> APIResponse<T> getDroneBySerialNumber(String serialNumber) {
		DroneRegisterRequest drone  = new DroneRegisterRequest();
		drone.setSerialNumber(serialNumber);
		return (doesDroneSerialExists(drone)) ? (APIResponse<T>) (apiResponse.buildAPIResponse(droneRepository.findById(serialNumber), Constants.STATUS_CODE_SUCCESS)): (APIResponse<T>) (apiResponse.buildAPIResponse(Constants.STATUS_DESC_DRONE_DOESNT_EXIST.concat(serialNumber), Constants.STATUS_CODE_DRONE_DOESNT_EXIST));
	}



	@Override
	public <T> APIResponse<T> checkDroneBatteryLevel(String serialNumber) {
		DroneRegisterRequest drone  = new DroneRegisterRequest();
		drone.setSerialNumber(serialNumber);
		return (doesDroneSerialExists(drone)) ? (APIResponse<T>) (apiResponse.buildAPIResponse(new DroneBatteryLevelResponse(droneRepository.findById(serialNumber).get().getCapacity()), Constants.STATUS_CODE_SUCCESS)): (APIResponse<T>) (apiResponse.buildAPIResponse(Constants.STATUS_DESC_DRONE_DOESNT_EXIST.concat(serialNumber), Constants.STATUS_CODE_DRONE_DOESNT_EXIST));

		
	}



	@Override
	public <T> APIResponse<T> checkingAvailableDrones() {
		
		return (APIResponse<T>) apiResponse.buildAPIResponse(droneRepository.findByState(Constants.IDLE), Constants.STATUS_CODE_SUCCESS);
	}


	@Override
	public List<Drone> getAllDrones() {
		
		return droneRepository.findAll();
	}

	@Override
	public <T> APIResponse<T> loadDroneWithMedication(LoadDroneRequest request) {
		
		//get the idle drone 
		Optional<Drone> optionalDrone = Optional.empty();
		optionalDrone =	droneRepository.getDroneByIdAndState(request.getSerialNumber() ,Constants.IDLE);
		
		
		if(optionalDrone.isPresent()) {
			
			//check battery level
			if(optionalDrone.get().getCapacity()<25) {
				return (APIResponse<T>) apiResponse.buildAPIResponse(Constants.STATUS_DESC_DRONE_BATTERY_LEVEL_LOW, Constants.STATUS_CODE_DRONE_BATTERY_LEVEL_LOW);
			}
			
			List<knapSackObject> kso = new ArrayList<>();
			for(MedicationRequest medReq: request.getMedicationRequest()) {
				
				//save meds to db and polupate knapsack list
				Medication saveMedicationToDB = new Medication();
				saveMedicationToDB.setCode(medReq.getCode());
				saveMedicationToDB.setDrone(request.getSerialNumber());
				saveMedicationToDB.setLoaded(0);
				saveMedicationToDB.setName(medReq.getName());
				saveMedicationToDB.setPicture(null);
				saveMedicationToDB.setWeight(medReq.getWeight());
				medicationRepository.save(saveMedicationToDB);
				
				kso.add(new knapSackObject(medReq.getName(), medReq.getWeight(), medReq.getValue()));
			}
			
			//update drone to Loading
			Drone updateDroneToLoading = optionalDrone.get();
			updateDroneToLoading.setState(Constants.STATUS_DESC_LOADING);
			droneRepository.save(updateDroneToLoading);
			
			
			//APPLY KNAP SACK ALGORITHM
			ZeroOneKnapsackEngine zokEngine=    new ZeroOneKnapsackEngine(kso, optionalDrone.get().getWeight());
			List<LoadedMedication> lst = zokEngine.getLoadedMedicationList();
			
			for(LoadedMedication lm :lst) {
				
			List<Medication> medsToUpdateList=	medicationRepository.getMedicationByName(lm.getMedicationName()) ;
				
				for(Medication medToUpdate :medsToUpdateList) {
					medToUpdate.setLoaded(1);
					medicationRepository.save(medToUpdate);
				}
				
			}
			
			//update drone to Loading
			Drone updateDroneToLoaded= optionalDrone.get();
			updateDroneToLoading.setState(Constants.STATUS_DESC_LOADED);
			droneRepository.save(updateDroneToLoading);
			
			
		  return (APIResponse<T>) apiResponse.buildAPIResponse(lst, Constants.STATUS_CODE_SUCCESS);
			
		}else {
			
			
			return (APIResponse<T>) apiResponse.buildAPIResponse("Drone either Invalid or its not Idle", Constants.STATUS_CODE_DRONE_DOESNT_EXIST);
		}
		
		
	
	}



	@Override
	public <T> APIResponse<T> getMedicationLoadedInDrone(String serialNumber) {
		
		;
		 return (APIResponse<T>) apiResponse.buildAPIResponse(medicationRepository.getMedicationLoadedInDrone(serialNumber, 1), Constants.STATUS_CODE_SUCCESS);
		
	}

}
