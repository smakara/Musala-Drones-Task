package com.musala.drone.task.controller;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musala.drone.task.dto.APIResponse;
import com.musala.drone.task.dto.DroneRegisterRequest;
import com.musala.drone.task.service.DroneService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/drone")
@RequiredArgsConstructor
@Slf4j
public class DroneController {
	
	public final DroneService  droneService;

	
	@PostMapping(value = "/register")
	public  <T> APIResponse<T> registerDrone (@Validated @RequestBody DroneRegisterRequest request ) {
			log.info("DroneRegisterRequest : {}" , request);
		return 	droneService.register (request);
	}
	

	
	@GetMapping(value = "/serial/{serialNumber}")
	public  <T> APIResponse<T> getDroneBySerialNumber (@PathVariable String serialNumber ) {
		log.info("getDroneBySerialNumber Request : {}" , serialNumber);
	return 	droneService.getDroneBySerialNumber (serialNumber);
}

	
	@GetMapping(value = "/capacity/{serialNumber}")
	public  <T> APIResponse<T> checkDroneBatteryLevel (@PathVariable String serialNumber ) {
		log.info("getDroneBySerialNumber Request : {}" , serialNumber);
	return 	droneService.checkDroneBatteryLevel (serialNumber);
}
	
	
	@GetMapping(value = "/available")
	public  <T> APIResponse<T> checkingAvailableDrones () {
		log.info("checkingAvailableDrones Request ");
	return 	droneService.checkingAvailableDrones ();
}
	
}
