package com.musala.drone.task.model;

import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="audit_event_log")
@NoArgsConstructor
public class AuditEventLog {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public long id ;
	
	@Column(name="drone_serial_number")
	public String  droneSerialNumber ;
	
	
	@Column(name="drone_capacity")
	public Float  droneCapacity ;
	
	
	@Column(name="date_created")
	public LocalDateTime  dateCreated ;

}
