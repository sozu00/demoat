package com.jiniguez.demo.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jiniguez.demo.DTO.DoctorDTO;
import com.jiniguez.demo.Model.Doctor;

@Repository
public interface DoctorDAO extends CrudRepository<Doctor, Integer>{

	@Query(value = "select new com.jiniguez.demo.DTO.DoctorDTO(d.id, d.name, d.email) from Doctor d  "
			+ " join d.consultations c "
			+ " join c.appointments a"
			+ " group by d.id"
			+ " order by count(distinct a.patient) desc")
	List<DoctorDTO> findTopNDoctorsWithMorePatients();
/*	QUERY
 	select d.id, count(distinct patient_id) 
  	from doctor d, appointment a, consultation c 
  	where d.id = c.doctor_id
  	and a.consultation_id = c.id 
  	group by d.id
  	order by count(distinct a.patient_id) DESC
 */
}
