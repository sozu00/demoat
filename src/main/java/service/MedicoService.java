package service;

import org.springframework.stereotype.Service;

import model.Medico;

@Service
public interface MedicoService {

	Medico findById(Integer id);
	
//	@Query(value = "from User u where u.name like :name")
//	public List<User> findByName(@Param("name") String paramName);
}
