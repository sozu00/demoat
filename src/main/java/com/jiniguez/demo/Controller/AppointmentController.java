package com.jiniguez.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jiniguez.demo.DTO.AppointmentDTO;
import com.jiniguez.demo.Service.AppointmentService;

@RestController
@RequestMapping(value = "api/appointment")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	public List<AppointmentDTO> findAll(@RequestParam(required=false) Integer page,
			@RequestParam(required = false) Integer size){
		return appointmentService.findAll(page, size);
	}
	
	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public AppointmentDTO findOneById(@PathVariable Integer id) {
		return appointmentService.findById(id);
	}
	
	@RequestMapping(method = {RequestMethod.POST})
	public AppointmentDTO create(@RequestBody AppointmentDTO appointment) {
		return appointmentService.create(appointment);
	}
	
	@RequestMapping(value = "/{id}",method = {RequestMethod.PUT})
	public void update(@RequestBody AppointmentDTO appointment, 
			@PathVariable("id") Integer id) {
		appointmentService.update(appointment);
	}
	
	@RequestMapping(value = "/{id}",method = {RequestMethod.PUT})
	public void delete(@RequestBody AppointmentDTO appointment, 
			@PathVariable("id") Integer id) {
		appointmentService.delete(id);
	}
}

