package com.jiniguez.demo.Service;

import java.text.ParseException;
import java.util.List;
import com.jiniguez.demo.DTO.ConsultationDTO;
import com.jiniguez.demo.DTO.DoctorDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Model.Consultation;

public interface ConsultationService {

	Consultation findById(Integer id) throws NotFoundException;
	
	DoctorDTO findDoctorByConsultaId(Integer id) throws NotFoundException;

	List<ConsultationDTO> findAll(Integer page, Integer size);

	ConsultationDTO create(ConsultationDTO consultation) throws  NotFoundException;

	void update(ConsultationDTO consultation) throws  NotFoundException;

	void delete(Integer id);

	ConsultationDTO findDTOById(Integer id) throws NotFoundException;
}
