package com.musala.drone.task.service;


import java.util.List;

import com.musala.drone.task.dto.APIResponse;
import com.musala.drone.task.dto.DroneRegisterRequest;
import com.musala.drone.task.dto.LoadDroneRequest;
import com.musala.drone.task.model.Drone;

public interface DroneService {

public	<T> APIResponse<T> register(DroneRegisterRequest request);
public	<T> APIResponse<T> getDroneBySerialNumber(String serialNumber);
public	<T> APIResponse<T> checkDroneBatteryLevel(String serialNumber);
public	<T> APIResponse<T> checkingAvailableDrones();
public	List<Drone> getAllDrones();
public	<T> APIResponse<T> loadDroneWithMedication(LoadDroneRequest request);
public <T> APIResponse<T> getMedicationLoadedInDrone(String serialNumber);
	
	
}
