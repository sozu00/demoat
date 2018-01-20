package com.jiniguez.demo.Service;

import java.util.List;
import com.jiniguez.demo.DTO.DoctorDTO;

public interface DoctorService {

	DoctorDTO findById(Integer id);

	List<DoctorDTO> findAll(Integer page, Integer size);

	DoctorDTO create(DoctorDTO doctor);

	void update(DoctorDTO doctor);

	void delete(Integer id);

	List<DoctorDTO> findByName(String name);

}
