package service;

import org.springframework.stereotype.Service;

import model.Consulta;

@Service
public interface ConsultaService {

	Consulta findById(Integer id);
	
//	@Query(value = "from User u where u.name like :name")
//	public List<User> findByName(@Param("name") String paramName);
}
