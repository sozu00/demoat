package service;

import java.util.List;

import org.springframework.stereotype.Service;

import DTO.ConsultationDTO;
import model.Doctor;

@Service
public interface ConsultationService {

	ConsultationDTO findById(Integer id);
	
	Doctor findDoctorByConsultaId(Integer id);

	List<ConsultationDTO> findAll(Integer page, Integer size);

	ConsultationDTO create(ConsultationDTO consultation);

	void update(ConsultationDTO consultation);

	void delete(Integer id);
}
