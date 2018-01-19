package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DTO.ConsultationDTO;
import service.ConsultationService;

@RestController
@RequestMapping(value = "api/user")
public class ConsultationController {
	
	@Autowired
	private ConsultationService consultationService;
	
	public List<ConsultationDTO> findAll(@RequestParam(required=false) Integer page,
			@RequestParam(required = false) Integer size){
		return consultationService.findAll(page, size);
	}
	
	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public ConsultationDTO findOneById(@PathVariable Integer id) {
		return consultationService.findById(id);
	}
	
	@RequestMapping(method = {RequestMethod.POST})
	public ConsultationDTO create(@RequestBody ConsultationDTO consultation) {
		return consultationService.create(consultation);
	}
	
	@RequestMapping(value = "/{id}",method = {RequestMethod.PUT})
	public void update(@RequestBody ConsultationDTO consultation, 
			@PathVariable("id") Integer id) {
		consultationService.update(consultation);
	}
	
	@RequestMapping(value = "/{id}",method = {RequestMethod.PUT})
	public void delete(@RequestBody ConsultationDTO consultation, 
			@PathVariable("id") Integer id) {
		consultationService.delete(id);
	}
}

