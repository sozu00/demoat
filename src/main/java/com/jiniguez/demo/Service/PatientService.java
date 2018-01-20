package com.jiniguez.demo.Service;

import java.util.List;
import com.jiniguez.demo.DTO.PatientDTO;

public interface PatientService {

	PatientDTO findById(Integer id);

	List<PatientDTO> findAll(Integer page, Integer size);

	PatientDTO create(PatientDTO patient);

	void update(PatientDTO patient);

	void delete(Integer id);
	
}
