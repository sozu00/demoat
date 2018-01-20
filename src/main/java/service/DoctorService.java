package service;

import java.util.List;

import org.springframework.stereotype.Service;

import DTO.DoctorDTO;

@Service
public interface DoctorService {

	DoctorDTO findById(Integer id);

	List<DoctorDTO> findAll(Integer page, Integer size);

	DoctorDTO create(DoctorDTO doctor);

	void update(DoctorDTO doctor);

	void delete(Integer id);

}
