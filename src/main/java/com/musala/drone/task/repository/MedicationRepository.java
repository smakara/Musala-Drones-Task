package com.musala.drone.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.musala.drone.task.model.Medication;

public interface MedicationRepository  extends JpaRepository<Medication, String>{
	
	
	@Query("FROM Medication WHERE name= :name ")
	public List <Medication> getMedicationByName (String name) ;
	
	
	@Query("FROM Medication WHERE drone =:drone and loaded=:loaded")
	public List <Medication> getMedicationLoadedInDrone (String drone,int loaded) ;

}
