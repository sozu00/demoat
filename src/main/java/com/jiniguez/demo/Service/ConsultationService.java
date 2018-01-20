package com.jiniguez.demo.Service;

import java.util.List;
import com.jiniguez.demo.DTO.ConsultationDTO;
import com.jiniguez.demo.DTO.DoctorDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;

public interface ConsultationService {

	ConsultationDTO findById(Integer id) throws NotFoundException;
	
	DoctorDTO findDoctorByConsultaId(Integer id) throws NotFoundException;

	List<ConsultationDTO> findAll(Integer page, Integer size);

	ConsultationDTO create(ConsultationDTO consultation);

	void update(ConsultationDTO consultation);

	void delete(Integer id);
}
