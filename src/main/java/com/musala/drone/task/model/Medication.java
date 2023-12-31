package com.musala.drone.task.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name="medication")
@NoArgsConstructor
public class Medication {

	@Id
	@Pattern(
		regexp = "[A-Z0-9_]+",
		message = "only upper case letters, underscore and numbers allowed"
	)
	public String code;

	@Column(nullable = false)
	@Pattern(
		regexp = "[a-zA-Z_0-9-]+",
		message = "only letters, numbers, underscore and hyphen allowed"
	)
	public String name;

	@Column
	public String picture;

	@Column (nullable = false)
	public int weight;
	
	@Column
	public String drone;
	
	@Column
	public int loaded ;// carried no not

	
}