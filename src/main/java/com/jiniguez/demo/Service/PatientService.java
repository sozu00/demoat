package com.jiniguez.demo.Service;

import java.util.List;
import com.jiniguez.demo.DTO.PatientDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;

public interface PatientService {

	PatientDTO findById(Integer id) throws NotFoundException;

	List<PatientDTO> findAll(Integer page, Integer size);

	PatientDTO create(PatientDTO patient);

	void update(PatientDTO patient);

	void delete(Integer id);
	
}
