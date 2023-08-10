package com.musala.drone.task.service;


import java.util.List;

import com.musala.drone.task.dto.APIResponse;
import com.musala.drone.task.dto.DroneRegisterRequest;
import com.musala.drone.task.model.Drone;

public interface DroneService {

	<T> APIResponse<T> register(DroneRegisterRequest request);
	<T> APIResponse<T> getDroneBySerialNumber(String serialNumber);
	<T> APIResponse<T> checkDroneBatteryLevel(String serialNumber);
	<T> APIResponse<T> checkingAvailableDrones();
	List<Drone> getAllDrones();
	
	

}
