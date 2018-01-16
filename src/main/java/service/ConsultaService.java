package service;

import org.springframework.stereotype.Service;

import model.Consulta;
import model.Medico;

@Service
public interface ConsultaService {

	Consulta findById(Integer id);
	
	Medico findDoctorByConsultaId(Integer id);
//	@Query(value = "from User u where u.name like :name")
//	public List<User> findByName(@Param("name") String paramName);
}
