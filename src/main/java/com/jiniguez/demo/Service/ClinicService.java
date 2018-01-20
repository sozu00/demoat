package com.jiniguez.demo.Service;

import java.util.List;

import com.jiniguez.demo.DTO.ClinicDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;

public interface ClinicService {

	public ClinicDTO findById(Integer id) throws NotFoundException;

	public List<ClinicDTO> findAll(Integer page, Integer size);

	public ClinicDTO create(ClinicDTO clinic);

	public void update(ClinicDTO clinic);

	public void delete(Integer id);
	
}
