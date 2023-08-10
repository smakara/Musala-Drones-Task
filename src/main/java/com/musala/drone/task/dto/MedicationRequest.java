package com.musala.drone.task.dto;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
@NoArgsConstructor
public class MedicationRequest {

	@Pattern(
		regexp = "[A-Z0-9_]+",
		message = "only upper case letters, underscore and numbers allowed"
	)
	public String code;
	
	@Pattern(
		regexp = "[a-zA-Z_0-9-]+",
		message = "only letters, numbers, underscore and hyphen allowed"
	)
	public String name;

	public String picture;
	
	public int weight;
	
	public int value;

	
}