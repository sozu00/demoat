package com.jiniguez.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jiniguez.demo.DTO.ClinicDTO;
import com.jiniguez.demo.DTO.DoctorDTO;
import com.jiniguez.demo.DTO.PatientDTO;
import com.jiniguez.demo.DTO.RoomDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Service.ClinicService;

import lombok.extern.java.Log;

@RestController
@RequestMapping(value = "api/clinic")
@Log
public class ClinicController {
	
	@Autowired
	private ClinicService clinicService;
		
	@RequestMapping(method = { RequestMethod.GET })
	public List<ClinicDTO> findAll(@RequestParam(required=false) Integer page,
			@RequestParam(required = false) Integer size){
		log.info("Buscando todas las clinicas");
		return clinicService.findAll(page, size);
	}
	
	@RequestMapping(value= "/{id}/patients", method = { RequestMethod.GET })
	public List<PatientDTO> findPatients(@PathVariable Integer id) throws NotFoundException{
		log.info("Buscando los paciente de la clinica con id = "+id);
		return clinicService.findPatients(id);
	}

	
	@RequestMapping(value= "/{id}/doctors", method = { RequestMethod.GET })
	public List<DoctorDTO> findDoctors(@PathVariable Integer id) throws NotFoundException{
		log.info("Buscando los doctores de la clinica con id = "+id);
		return clinicService.findDoctors(id);
	}
	
	@RequestMapping(value= "/{id}/rooms", method = { RequestMethod.GET })
	public List<RoomDTO> findRooms(@PathVariable Integer id) throws NotFoundException{
		log.info("Buscando las habitaciones de la clinica con id = "+id);
		return clinicService.findRooms(id);
	}

	
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public ClinicDTO findOneById(@PathVariable Integer id) throws NotFoundException {
		log.info("Buscando la clinica con id = "+id);
		return clinicService.findDTOById(id);
	}
	
	@RequestMapping(method = {RequestMethod.POST})
	public ClinicDTO create(@RequestBody ClinicDTO clinic) {
		return clinicService.create(clinic);
	}
	
	@RequestMapping(value = "/{id}",method = {RequestMethod.PUT})
	public void update(@RequestBody ClinicDTO clinic, 
			@PathVariable("id") Integer id) {
		clinicService.update(clinic);
	}
	
	@RequestMapping(value = "/{id}",method = {RequestMethod.DELETE})
	public void delete(@RequestBody ClinicDTO clinic, 
			@PathVariable("id") Integer id) {
		clinicService.delete(id);
	}
}

