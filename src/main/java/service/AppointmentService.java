package service;

import java.util.List;

import org.springframework.stereotype.Service;

import DTO.AppointmentDTO;
import model.Appointment;
import model.User;

@Service
public interface AppointmentService {

	/**
	 * Encuentra una cita a partir de su id
	 * @param id
	 * @return Objeto cita
	 */
	AppointmentDTO findById(Integer id);

	/**
	 * Encuentra todas las cita paginables
	 * @param page
	 * @param size
	 * @return Listado de citas
	 */
	List<AppointmentDTO> findAll(Integer page, Integer size);

	/**
	 * Crea y devuelve la cita 
	 * @param appointment
	 * @return la cita creada
	 */
	AppointmentDTO create(AppointmentDTO appointment);

	/**
	 * Actualiza la cita
	 * @param appointment
	 */
	void update(AppointmentDTO appointment);

	/**
	 * Elimina la cita a partir del id
	 * @param id
	 */
	void delete(Integer id);
	
//	@Query(value = "from User u where u.name like :name")
//	public List<User> findByName(@Param("name") String paramName);
}
