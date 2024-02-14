package com.cp.springboot.appmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cp.springboot.appmanagement.model.AppManagement;

public interface AppointmentRepository extends JpaRepository<AppManagement, Long> {
	List<AppManagement> findByUserName(String user);
	
}
