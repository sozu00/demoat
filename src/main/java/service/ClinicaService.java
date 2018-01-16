package service;

import org.springframework.stereotype.Service;

import model.Clinica;

@Service
public interface ClinicaService {

	Clinica findById(Integer id);
	
//	@Query(value = "from User u where u.name like :name")
//	public List<User> findByName(@Param("name") String paramName);
}
