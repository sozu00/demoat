package service;

import org.springframework.stereotype.Service;

import model.Cita;

@Service
public interface CitaService {

	Cita findById(Integer id);
	
//	@Query(value = "from User u where u.name like :name")
//	public List<User> findByName(@Param("name") String paramName);
}
