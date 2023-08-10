package com.musala.drone.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.musala.drone.task.model.Drone;




/**
 * @author shepherd
 *
 */

public interface DroneRepository extends JpaRepository<Drone, String> {
	
	
/**
 * @param state
 * @return
 */
public 	List<Drone> findByState(String state);


}
