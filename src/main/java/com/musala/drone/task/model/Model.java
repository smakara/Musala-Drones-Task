package com.musala.drone.task.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="model")
public class Model {
	

	@Id
	@Column(name="id")
	public long id ;
	
	@Column(name="model_desc")
	public String  modelDesc ;
	
}
