package com.jiniguez.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jiniguez.demo.DTO.ConsultationDTO;
import com.jiniguez.demo.DTO.DoctorDTO;

@Service
public interface ConsultationService {

	ConsultationDTO findById(Integer id);
	
	DoctorDTO findDoctorByConsultaId(Integer id);

	List<ConsultationDTO> findAll(Integer page, Integer size);

	ConsultationDTO create(ConsultationDTO consultation);

	void update(ConsultationDTO consultation);

	void delete(Integer id);
}
