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

import com.jiniguez.demo.DTO.ConsultationDTO;
import com.jiniguez.demo.DTO.DoctorDTO;
import com.jiniguez.demo.DTO.PatientDTO;
import com.jiniguez.demo.DTO.StatisticsDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Service.DoctorService;

import lombok.extern.java.Log;

@RestController
@RequestMapping(value = "api/doctor")
@Log
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@RequestMapping(method = {RequestMethod.GET})
	public List<DoctorDTO> findAll(@RequestParam(required=false) Integer page,
			@RequestParam(required = false) Integer size){
		log.info("Buscando todos los doctores");
		return doctorService.findAll(page, size);
	}
	
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public DoctorDTO findOneById(@PathVariable Integer id) throws NotFoundException{
		log.info("Buscando el doctor con id = "+id);
		return doctorService.findDTOById(id);
	}
	
	@RequestMapping(value = "/external/{id}", method = { RequestMethod.GET })
	public DoctorDTO findOneByExternalId(@RequestParam(required=true) String id) throws NotFoundException{
		log.info("Buscando el doctor con idExterna = "+id);
		return doctorService.doctorToDTO(doctorService.findByExternalId(id));
	}
	
	@RequestMapping(value = "/{id}/patients", method = { RequestMethod.GET })
	public List<PatientDTO> findPatients(@PathVariable Integer id) throws NotFoundException{
		log.info("Buscando los pacientes del doctor con id = "+id);
		return doctorService.findPatients(id);
	}
	
	@RequestMapping(value = "/{id}/consultations", method = { RequestMethod.GET })
	public List<ConsultationDTO> findConsultations(@PathVariable Integer id) throws NotFoundException{
		log.info("Buscando las consultas del doctor con id = "+id);
		return doctorService.findConsultations(id);
	}
	
	@RequestMapping(value = "/name={name}", method = { RequestMethod.GET })
	public List<DoctorDTO> findByName(@PathVariable String name) {
		log.info("Buscando el doctor con nombre = "+name);
		return doctorService.findByName(name);
	}
	
	@RequestMapping(value = "/top={num}", method = { RequestMethod.GET })
	public List<DoctorDTO> findTopNDoctorsWithMorePatients(@PathVariable Integer num) {
		log.info("Buscando los "+num+ " doctores con mas pacientes");
		return doctorService.findTopNDoctorsWithMorePatients(num);
	}
	
	@RequestMapping(method = {RequestMethod.POST})
	public DoctorDTO create(@RequestBody DoctorDTO doctor) {
		return doctorService.create(doctor);
	}
	
	@RequestMapping(value = "/{id}",method = {RequestMethod.PUT})
	public void update(@RequestBody DoctorDTO doctor, 
			@PathVariable("id") Integer id) {
		doctorService.update(doctor);
	}
	
	@RequestMapping(value = "/{id}",method = {RequestMethod.DELETE})
	public void delete(@RequestBody DoctorDTO doctor, 
			@PathVariable("id") Integer id) {
		doctorService.delete(id);
	}
	
	@RequestMapping(value = "/stats",method = {RequestMethod.GET})
	public List<StatisticsDTO> getStats(@RequestParam(required=false) String initDate,
			@RequestParam(required = false) String endDate) throws NotFoundException, ParseException {
		log.info("Mostrando todos lo doctores que han pasado consulta entre "+initDate+" y "+endDate+", cuantas consultas y cuanto dinero han ganado.");
		return doctorService.getStats(initDate, endDate);
	}
}

