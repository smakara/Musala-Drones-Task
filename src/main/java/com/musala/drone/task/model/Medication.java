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
	private String code;

	@Column(nullable = false)
	@Pattern(
		regexp = "[a-zA-Z_0-9-]+",
		message = "only letters, numbers, underscore and hyphen allowed"
	)
	private String name;

	@Column
	private String picture;

	@Column (nullable = false)
	private Integer weight;

	
}