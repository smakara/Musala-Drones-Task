package com.musala.drone.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musala.drone.task.model.AuditEventLog;

public interface AuditEventLogRepository  extends JpaRepository<AuditEventLog, Long>{

}
