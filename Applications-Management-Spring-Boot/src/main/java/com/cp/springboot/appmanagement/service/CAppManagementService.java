package com.cp.springboot.appmanagement.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cp.springboot.appmanagement.model.AppManagement;
import com.cp.springboot.appmanagement.repository.AppointmentRepository;
@Service
public class CAppManagementService implements AppManagementService {
	
	@Autowired
	private AppointmentRepository appointmentRepository;

	@Override
	public List<AppManagement> getAppointmentByUser(String user) {
		// TODO Auto-generated method stub
		return appointmentRepository.findByUserName(user);
	}

	@Override
	public Optional<AppManagement> getAppointmentById(long id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(appointmentRepository.findOne(id));
	}

	@Override
	public void updateAppointment(AppManagement appointment) {
		// TODO Auto-generated method stub
		appointmentRepository.save(appointment);
	}

	@Override
	public void addAppointment(String name, String desc, Date targetDate, boolean isDone) {
		// TODO Auto-generated method stub
		appointmentRepository.save(new AppManagement(name,desc,targetDate,isDone));
	}

	@Override
	public void deleteAppointment(long id) {
		// TODO Auto-generated method stub
		Optional<AppManagement> appointment=Optional.ofNullable(appointmentRepository.findOne(id));
		if(appointment.isPresent()) {
			appointmentRepository.delete(appointment.get());
		}
	}

	@Override
	public void saveAppointment(AppManagement appointment) {
		// TODO Auto-generated method stub
		appointmentRepository.save(appointment);
	}

}
