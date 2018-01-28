package com.jiniguez.demo.Service;

import java.text.ParseException;
import java.util.List;

import com.jiniguez.demo.DTO.ConsultationDTO;
import com.jiniguez.demo.DTO.DoctorDTO;
import com.jiniguez.demo.DTO.PatientDTO;
import com.jiniguez.demo.DTO.StatisticsDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Model.Doctor;

public interface DoctorService {

	Doctor findById(Integer id) throws NotFoundException;

	List<DoctorDTO> findAll(Integer page, Integer size);

	DoctorDTO create(DoctorDTO doctor);

	void update(DoctorDTO doctor);

	void delete(Integer id);

	List<DoctorDTO> findByName(String name);

	List<DoctorDTO> findTopNDoctorsWithMorePatients(Integer num);

	DoctorDTO findDTOById(Integer id) throws NotFoundException;

	DoctorDTO doctorToDTO(Doctor doctor);

	Doctor DTOToDoctor(DoctorDTO doctor);

	List<PatientDTO> findPatients(Integer id) throws NotFoundException;

	List<ConsultationDTO> findConsultations(Integer id) throws NotFoundException;

	List<StatisticsDTO> getStats(String initDate, String endDate) throws ParseException;

	Doctor findByExternalId(String externalID);

	void init();
}
