package com.jiniguez.demo.Service;

import java.util.List;

import com.jiniguez.demo.DTO.AppointmentDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;


public interface AppointmentService {

	/**
	 * Encuentra una cita a partir de su id
	 * @param id
	 * @return Objeto cita
	 * @throws NotFoundException 
	 */
	AppointmentDTO findById(Integer id) throws NotFoundException;

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
