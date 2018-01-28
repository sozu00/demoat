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
import com.jiniguez.demo.DTO.PatientDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Service.PatientService;

import lombok.extern.java.Log;

@RestController
@RequestMapping(value = "api/patient")
@Log
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@RequestMapping(method = { RequestMethod.GET })
	public List<PatientDTO> findAll(@RequestParam(required=false) Integer page,
			@RequestParam(required = false) Integer size){
		log.info("Buscando todos los pacientes");
		return patientService.findAll(page, size);
	}
	
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public PatientDTO findOneById(@PathVariable Integer id) throws NotFoundException {
		log.info("Buscando el paciente con id = "+id);
		return patientService.findDTOById(id);
	}
	
	@RequestMapping(value = "/{id}/appointment", method = { RequestMethod.GET })
	public List<AppointmentDTO> findAppointments(@PathVariable Integer id) throws NotFoundException {
		log.info("Buscando las citas del paciente con id = "+id);
		return patientService.findAppointments(id);
	}
	
	@RequestMapping(method = {RequestMethod.POST})
	public PatientDTO create(@RequestBody PatientDTO patient) {
		return patientService.create(patient);
	}
	
	@RequestMapping(value = "/{id}",method = {RequestMethod.PUT})
	public void update(@RequestBody PatientDTO patient, 
			@PathVariable("id") Integer id) {
		patientService.update(patient);
	}
	
	@RequestMapping(value = "/{id}",method = {RequestMethod.DELETE})
	public void delete(@RequestBody PatientDTO patient, 
			@PathVariable("id") Integer id) {
		patientService.delete(id);
	}
}

