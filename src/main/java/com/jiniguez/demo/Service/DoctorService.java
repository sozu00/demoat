package com.jiniguez.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jiniguez.demo.DTO.DoctorDTO;

@Service
public interface DoctorService {

	DoctorDTO findById(Integer id);

	List<DoctorDTO> findAll(Integer page, Integer size);

	DoctorDTO create(DoctorDTO doctor);

	void update(DoctorDTO doctor);

	void delete(Integer id);

}
