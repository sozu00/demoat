package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DTO.DoctorDTO;
import service.DoctorService;

@RestController
@RequestMapping(value = "api/user")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	public List<DoctorDTO> findAll(@RequestParam(required=false) Integer page,
			@RequestParam(required = false) Integer size){
		return doctorService.findAll(page, size);
	}
	
	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public DoctorDTO findOneById(@PathVariable Integer id) {
		return doctorService.findById(id);
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
	
	@RequestMapping(value = "/{id}",method = {RequestMethod.PUT})
	public void delete(@RequestBody DoctorDTO doctor, 
			@PathVariable("id") Integer id) {
		doctorService.delete(id);
	}
}

