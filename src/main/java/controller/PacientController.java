package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DTO.PacientDTO;
import service.PacientService;

@RestController
@RequestMapping(value = "api/user")
public class PacientController {
	
	@Autowired
	private PacientService pacientService;
	
	public List<PacientDTO> findAll(@RequestParam(required=false) Integer page,
			@RequestParam(required = false) Integer size){
		return pacientService.findAll(page, size);
	}
	
	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public PacientDTO findOneById(@PathVariable Integer id) {
		return pacientService.findById(id);
	}
	
	@RequestMapping(method = {RequestMethod.POST})
	public PacientDTO create(@RequestBody PacientDTO pacient) {
		return pacientService.create(pacient);
	}
	
	@RequestMapping(value = "/{id}",method = {RequestMethod.PUT})
	public void update(@RequestBody PacientDTO pacient, 
			@PathVariable("id") Integer id) {
		pacientService.update(pacient);
	}
	
	@RequestMapping(value = "/{id}",method = {RequestMethod.PUT})
	public void delete(@RequestBody PacientDTO pacient, 
			@PathVariable("id") Integer id) {
		pacientService.delete(id);
	}
}

