package service;

import java.util.List;

import org.springframework.stereotype.Service;

import DTO.PacientDTO;
import model.Pacient;

@Service
public interface PacientService {

	PacientDTO findById(Integer id);

	List<PacientDTO> findAll(Integer page, Integer size);

	PacientDTO create(PacientDTO pacient);

	void update(PacientDTO pacient);

	void delete(Integer id);
	
}
