package service;

import java.util.List;

import org.springframework.stereotype.Service;

import DTO.PatientDTO;
import model.Patient;

@Service
public interface PatientService {

	PatientDTO findById(Integer id);

	List<PatientDTO> findAll(Integer page, Integer size);

	PatientDTO create(PatientDTO patient);

	void update(PatientDTO patient);

	void delete(Integer id);
	
}
