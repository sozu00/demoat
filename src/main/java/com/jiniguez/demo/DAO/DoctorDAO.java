package com.jiniguez.demo.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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
//	value = "select doctor_id, count(distinct patient_id) from appointment, consultation where consultation_id = consultation.id group by doctor_id"
}
