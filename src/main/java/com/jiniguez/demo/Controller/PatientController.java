package com.jiniguez.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jiniguez.demo.DTO.PatientDTO;
import com.jiniguez.demo.Service.PatientService;

@RestController
@RequestMapping(value = "api/patient")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	public List<PatientDTO> findAll(@RequestParam(required=false) Integer page,
			@RequestParam(required = false) Integer size){
		return patientService.findAll(page, size);
	}
	
	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public PatientDTO findOneById(@PathVariable Integer id) {
		return patientService.findById(id);
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
	
	@RequestMapping(value = "/{id}",method = {RequestMethod.PUT})
	public void delete(@RequestBody PatientDTO patient, 
			@PathVariable("id") Integer id) {
		patientService.delete(id);
	}
}

