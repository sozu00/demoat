package service;

import org.springframework.stereotype.Service;

import model.Consultation;
import model.Doctor;

@Service
public interface ConsultationService {

	Consultation findById(Integer id);
	
	Doctor findDoctorByConsultaId(Integer id);
//	@Query(value = "from User u where u.name like :name")
//	public List<User> findByName(@Param("name") String paramName);
}
