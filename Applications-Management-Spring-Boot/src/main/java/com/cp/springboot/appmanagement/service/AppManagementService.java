package com.cp.springboot.appmanagement.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.cp.springboot.appmanagement.model.AppManagement;

public interface AppManagementService {
	List<AppManagement> getAppointmentByUser(String user);
	Optional<AppManagement> getAppointmentById(long id);
	void updateAppointment(AppManagement appointment);
	void addAppointment(String name, String desc, Date targetDate, boolean isDone);
	void deleteAppointment(long id);
	void saveAppointment(AppManagement appointment);
}
