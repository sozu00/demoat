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
import com.jiniguez.demo.DTO.ConsultationDTO;
import com.jiniguez.demo.DTO.PatientDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Service.AppointmentService;

import lombok.extern.java.Log;

@RestController
@RequestMapping(value = "api/appointment")
@Log
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@RequestMapping(method = { RequestMethod.GET })
	public List<AppointmentDTO> findAll(@RequestParam(required=false) Integer page,
			@RequestParam(required = false) Integer size){
		log.info("Buscando todos los usuarios");
		return appointmentService.findAll(page, size);
	}
	
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public AppointmentDTO findOneById(@PathVariable Integer id) throws NotFoundException {
		return appointmentService.findDTOById(id);
	}
	
	@RequestMapping(value = "/{id}/patient", method = { RequestMethod.GET })
	public PatientDTO findPatient(@PathVariable Integer id) throws NotFoundException {
		return appointmentService.findPatients(id);
	}

	@RequestMapping(value = "/{id}/consultation", method = { RequestMethod.GET })
	public ConsultationDTO findConsultation(@PathVariable Integer id) throws NotFoundException {
		return appointmentService.findConsultations(id);
	}
	
	@RequestMapping(method = {RequestMethod.POST})
	public AppointmentDTO create(@RequestBody AppointmentDTO appointment) throws NotFoundException {
		return appointmentService.create(appointment);
	}
	
	@RequestMapping(value = "/{id}",method = {RequestMethod.PUT})
	public void update(@RequestBody AppointmentDTO appointment, 
			@PathVariable("id") Integer id) throws NotFoundException {
		appointmentService.update(appointment);
	}
	
	@RequestMapping(value = "/{id}",method = {RequestMethod.DELETE})
	public void delete(@RequestBody AppointmentDTO appointment, 
			@PathVariable("id") Integer id) {
		appointmentService.delete(id);
	}
}

