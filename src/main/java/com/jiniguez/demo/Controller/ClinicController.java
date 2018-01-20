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
import com.jiniguez.demo.Service.ClinicService;

@RestController
@RequestMapping(value = "api/clinic")
public class ClinicController {
	
	@Autowired
	private ClinicService clinicService;
	
	@RequestMapping(method = { RequestMethod.GET })
	public List<ClinicDTO> findAll(@RequestParam(required=false) Integer page,
			@RequestParam(required = false) Integer size){
		return clinicService.findAll(page, size);
	}
	
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public ClinicDTO findOneById(@PathVariable Integer id) {
		return clinicService.findById(id);
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

