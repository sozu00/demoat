package com.jiniguez.demo.Service;

import java.util.List;

import com.jiniguez.demo.DTO.AppointmentDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Model.Appointment;


public interface AppointmentService {

	/**
	 * Encuentra una cita a partir de su id
	 * @param id
	 * @return Objeto cita
	 * @throws NotFoundException 
	 */
	Appointment findById(Integer id) throws NotFoundException;

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
	 * @throws NotFoundException 
	 */
	AppointmentDTO create(AppointmentDTO appointment) throws NotFoundException;

	/**
	 * Actualiza la cita
	 * @param appointment
	 * @throws NotFoundException 
	 */
	void update(AppointmentDTO appointment) throws NotFoundException;

	/**
	 * Elimina la cita a partir del id
	 * @param id
	 */
	void delete(Integer id);

	AppointmentDTO findDTOById(Integer id) throws NotFoundException;

	
//	@Query(value = "from User u where u.name like :name")
//	public List<User> findByName(@Param("name") String paramName);
}
