package com.jiniguez.demo.Service;

import java.util.List;

import com.jiniguez.demo.DTO.AppointmentDTO;
import com.jiniguez.demo.DTO.PatientDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Model.Patient;

public interface PatientService {

	Patient findById(Integer id) throws NotFoundException;

	List<PatientDTO> findAll(Integer page, Integer size);

	PatientDTO create(PatientDTO patient);

	void update(PatientDTO patient);

	void delete(Integer id);

	List<AppointmentDTO> findAppointments(Integer id) throws NotFoundException;

	PatientDTO findDTOById(Integer id) throws NotFoundException;

	PatientDTO patientToDTO(Patient patient);

	Patient DTOTopatient(PatientDTO patient);
	
}
