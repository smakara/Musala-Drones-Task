package com.musala.drone.task.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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


@Query("FROM Drone WHERE serialNumber= :serialNumber and state=:state")
public 	Optional<Drone> getDroneByIdAndState(String serialNumber ,String state);


}
