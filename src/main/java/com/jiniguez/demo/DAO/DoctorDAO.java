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

	@Query(value = "select d from Doctor d  "
			+ " join d.consultations c "
			+ " join c.appointments a"
			+ " group by d.id"
			+ " order by count(distinct a.patient) desc")
	List<Doctor> findTopNDoctorsWithMorePatients();
/*	QUERY
 	select d.id, count(distinct patient_id) 
  	from doctor d, appointment a, consultation c 
  	where d.id = c.doctor_id
  	and a.consultation_id = c.id 
  	group by d.id
  	order by count(distinct a.patient_id) DESC
 */

	/*
	 * Recupera todos los libros del usuario que contengan title y su id sea idUser
	 * select * from User
	 * inner join book on book.id_user = user.id
	 * where idUser = 7 and book.title like '%?%'
	 
	@Query(value = "select u from User as u inner join u.books b where b.title like :title and u.id like :id")
	List<User> findUserBooksLike(@Param(value = "idUser")Integer idUser, @Param(value = "title")String title);
	*/
}
