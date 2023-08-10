package com.musala.drone.task.dto;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.musala.drone.task.enums.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DroneRegisterRequest {
	
	
	@Id
	@Size(min =  1, max =  100)
	public String serialNumber;


	@Min(0)
	@Max(500)
	public int weight;


	@Min(0)
	@Max(100)
	public Double capacity;
	
	@NotNull(message = "Model is required ")
	public Model model;
	

}
