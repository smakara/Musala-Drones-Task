package com.musala.drone.task.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name="drone")
public class Drone {

	@Id
	@Size(min =  1, max =  100)
	@Column(name="serial_number")
	public String serialNumber;

	@Column (nullable = false)
	@Min(0)
	@Max(500)
	public int weight;

	@Column (nullable = false)
	@Min(0)
	@Max(100)
	public Double capacity;
	
	public String state;
	
	public String model;
}