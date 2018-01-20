package com.jiniguez.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jiniguez.demo.DTO.DoctorDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Service.DoctorService;

@RestController
@RequestMapping(value = "api/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<DoctorDTO> findAll(@RequestParam(required=false) Integer page,
			@RequestParam(required = false) Integer size){
		return doctorService.findAll(page, size);
	}
	
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public DoctorDTO findOneById(@PathVariable Integer id) throws NotFoundException{
		return doctorService.findById(id);
	}
	
	@RequestMapping(value = "/name={name}", method = { RequestMethod.GET })
	public List<DoctorDTO> findByName(@PathVariable String name) {
		return doctorService.findByName(name);
	}
	
	@RequestMapping(value = "/top={num}", method = { RequestMethod.GET })
	public List<DoctorDTO> findTopNDoctorsWithMorePatients(@PathVariable Integer num) {
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
}

