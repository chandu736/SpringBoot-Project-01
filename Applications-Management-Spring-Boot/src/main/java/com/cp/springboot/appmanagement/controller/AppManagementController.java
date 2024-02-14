package com.cp.springboot.appmanagement.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cp.springboot.appmanagement.model.AppManagement;
import com.cp.springboot.appmanagement.service.AppManagementService;

@Controller
public class AppManagementController {
	
	@Autowired
	private AppManagementService appointmentService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,false));
	}
	
	@RequestMapping(value="/list-appointment", method=RequestMethod.GET)
	public String showAppointment(ModelMap model) {
		String name=getLoggedInUserName(model);
		model.put("appointment", appointmentService.getAppointmentByUser(name));
		return "list-appointment";
	}
	
	public String getLoggedInUserName(ModelMap model) {
		Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		return principal.toString();
	}
	
	@RequestMapping(value="/add-appointment", method=RequestMethod.GET)
	public String showAddAppointmentPage(ModelMap model) {
		model.addAttribute("appointment",new AppManagement());
		return "appointment";
	}
	
	@RequestMapping(value="/delete-appointment",method=RequestMethod.GET)
	public String deleteAppointment(@RequestParam long id) {
		appointmentService.deleteAppointment(id);
		return "redirect:/list-appointment";
	}
	
	@RequestMapping(value="/update-appointment",method=RequestMethod.GET)
	public String showUpdateAppointment(@RequestParam long id, ModelMap model) {
		AppManagement appointment=appointmentService.getAppointmentById(id).get();
		model.put("appointment",appointment);
		return "appointment";
	}
	
	@RequestMapping(value="/update-appointment",method=RequestMethod.POST)
	public String updateAppointment(ModelMap model,@Valid AppManagement appointment, BindingResult result) {
		if(result.hasErrors()) {
			return "appointment";
		}
		appointment.setUserName(getLoggedInUserName(model));
		appointmentService.updateAppointment(appointment);
		return "redirect:/list-appointment";
	}
	
	@RequestMapping(value="/add-appointment",method=RequestMethod.POST)
	public String addAppointment(ModelMap model,AppManagement appointment,BindingResult result) {
		if(result.hasErrors()) {
			return "appointment";
		}
		appointment.setUserName(getLoggedInUserName(model));
		appointmentService.updateAppointment(appointment);
		return "redirect:/list-appointment";
	}
}
