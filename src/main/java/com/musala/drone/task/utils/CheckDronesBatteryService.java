package com.musala.drone.task.utils;

import java.security.cert.PKIXRevocationChecker.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.musala.drone.task.model.AuditEventLog;
import com.musala.drone.task.model.Drone;
import com.musala.drone.task.repository.AuditEventLogRepository;
import com.musala.drone.task.repository.DroneRepository;
import com.musala.drone.task.service.DroneService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shepherd
 *
 */
@RequiredArgsConstructor
@Slf4j
@Configuration
@EnableScheduling
@Service
public class CheckDronesBatteryService {
	
	public final AuditEventLogRepository  auditEventLogRepository ;
	public final DroneRepository droneRepository ;
	public final DroneService droneService ;
	@Scheduled(fixedRate = 90000)
	public void run() throws Exception {
		
		logBatteryLevel();
	}

	
	/**
	 *  Periodic Method to check drones battery levels and create history/audit event log for this.
	 *  
	 *  Assumptions : Some Electronic Devices embeded on the drone , will be sending live feeds of the battery capacity, which is here simulated by a 
	 *  				a random integer generator
	 *  
	 *  
	 */
	private void logBatteryLevel() {
		
		
	List<Drone> allDrones=	droneService.getAllDrones ();
	allDrones.stream().parallel().forEach(drone -> {
		
		int batteryLevel = mockElectronicFeedOfBatterCapacity() ;
		AuditEventLog ael = new AuditEventLog();
		ael.setDateCreated(LocalDateTime.now()) ;
		ael.setDroneCapacity((float) batteryLevel);
		ael.setDroneSerialNumber(drone.getSerialNumber()) ;
		auditEventLogRepository.save(ael);
		
		
		
		// updates the capacity level in the Drone table
		Optional<Drone> droneOption =  Optional.empty() ;
		droneOption= droneRepository.findById(drone.getSerialNumber()) ;
		if(droneOption.isPresent()) {
		
			Drone droneToBeUpdated = droneOption.get();
			droneToBeUpdated.setCapacity((double) batteryLevel) ;
			droneRepository.save(droneToBeUpdated) ;
			
		}

		
	});

	}
	
 public int	mockElectronicFeedOfBatterCapacity() {
	 Random rnd = new Random();
		 return   rnd.nextInt(99);
	 
 }

}
