package com.musala.drone.task.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.drone.task.dto.APIResponse;
import com.musala.drone.task.dto.DroneBatteryLevelResponse;
import com.musala.drone.task.dto.DroneRegisterRequest;
import com.musala.drone.task.enums.Model;
import com.musala.drone.task.model.Drone;
import com.musala.drone.task.repository.DroneRepository;
import com.musala.drone.task.service.APIResponseService;
import com.musala.drone.task.service.DroneService;
import com.musala.drone.task.utils.Constants;

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
	
	@Autowired
	public  APIResponseService apiResponse; ;

	
	
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

}
