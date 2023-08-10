package com.musala.drone.task.dto;

import java.util.List;

import com.musala.drone.task.enums.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadDroneRequest {
	
	public String serialNumber;
	
	public List<MedicationRequest> medicationRequest ;

}
