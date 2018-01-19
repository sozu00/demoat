package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DTO.ClinicDTO;
import service.ClinicService;

@RestController
@RequestMapping(value = "api/user")
public class ClinicController {
	
	@Autowired
	private ClinicService clinicService;
	
	public List<ClinicDTO> findAll(@RequestParam(required=false) Integer page,
			@RequestParam(required = false) Integer size){
		return clinicService.findAll(page, size);
	}
	
	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
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
	
	@RequestMapping(value = "/{id}",method = {RequestMethod.PUT})
	public void delete(@RequestBody ClinicDTO clinic, 
			@PathVariable("id") Integer id) {
		clinicService.delete(id);
	}
}

