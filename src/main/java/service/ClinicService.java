package service;

import java.util.List;

import org.springframework.stereotype.Service;

import DTO.ClinicDTO;

@Service
public interface ClinicService {

	public ClinicDTO findById(Integer id);

	public List<ClinicDTO> findAll(Integer page, Integer size);

	public ClinicDTO create(ClinicDTO clinic);

	public void update(ClinicDTO clinic);

	public void delete(Integer id);
	
}
