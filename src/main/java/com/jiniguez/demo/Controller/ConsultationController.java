package com.jiniguez.demo.Controller;

import java.text.ParseException;
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
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Service.ConsultationService;

import lombok.extern.java.Log;

@RestController
@RequestMapping(value = "api/consultation")
@Log
public class ConsultationController {
	
	@Autowired
	private ConsultationService consultationService;
	
	@RequestMapping(method = { RequestMethod.GET })
	public List<ConsultationDTO> findAll(@RequestParam(required=false) Integer page,
			@RequestParam(required = false) Integer size){
		log.info("Buscando todas las consultas");
		return consultationService.findAll(page, size);
	}
	
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public ConsultationDTO findOneById(@PathVariable Integer id) throws NotFoundException {
		log.info("Buscando la consulta con id = "+id);
		return consultationService.findDTOById(id);
	}
	
	@RequestMapping(value = "/{id}/appointments", method = { RequestMethod.GET })
	public List<AppointmentDTO> findAppointments(@PathVariable Integer id) throws NotFoundException {
		log.info("Buscando las citas de la consulta con id = "+id);
		return consultationService.findAppointments(id);
	}
	
	@RequestMapping(method = {RequestMethod.POST})
	public ConsultationDTO create(@RequestBody ConsultationDTO consultation) throws NotFoundException, ParseException {
		return consultationService.create(consultation);
	}
	
	@RequestMapping(value = "/{id}",method = {RequestMethod.PUT})
	public void update(@RequestBody ConsultationDTO consultation, 
			@PathVariable("id") Integer id) throws NotFoundException, ParseException {
		consultationService.update(consultation);
	}
	
	@RequestMapping(value = "/{id}",method = {RequestMethod.DELETE})
	public void delete(@RequestBody ConsultationDTO consultation, 
			@PathVariable("id") Integer id) {
		consultationService.delete(id);
	}
}

