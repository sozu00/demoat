package com.jiniguez.demo.Service.Implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiniguez.demo.DAO.AppointmentDAO;
import com.jiniguez.demo.DTO.AppointmentDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Model.Appointment;
import com.jiniguez.demo.Service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	AppointmentDAO appointmentDAO;
	
	@Autowired
	private DozerBeanMapper dozer;

	private AppointmentDTO appointmentToDTO(Appointment appointment) {
		return dozer.map(appointment, AppointmentDTO.class);
	}
	
	private Appointment DTOToAppointment(AppointmentDTO appointment) {
		return dozer.map(appointment, Appointment.class);
	}
	
	@Override
	public AppointmentDTO findById(Integer id) throws NotFoundException {
		Appointment a = Optional.ofNullable(appointmentDAO.findOne(id))
        		.orElseThrow(() -> new NotFoundException());
		return appointmentToDTO(a);
	}

	@Override
	public List<AppointmentDTO> findAll(Integer page, Integer size) {
		final Iterable<Appointment> findAll = appointmentDAO.findAll();
		final List<AppointmentDTO> res = new ArrayList<>();
		findAll.forEach(b ->{
				final AppointmentDTO aDTO = appointmentToDTO(b);
				res.add(aDTO);
			});
		return res;
	}
	
	@Override
	public AppointmentDTO create(AppointmentDTO appointment) {
		final Appointment a = DTOToAppointment(appointment);
		return appointmentToDTO(appointmentDAO.save(a));
	}

	@Override
	public void update(AppointmentDTO appointment) {
		final Appointment a = DTOToAppointment(appointment);
		appointmentDAO.save(a);		
	}

	@Override
	public void delete(Integer id) {
		appointmentDAO.delete(id);
	}
}
