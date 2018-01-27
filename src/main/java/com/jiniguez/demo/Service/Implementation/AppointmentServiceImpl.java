package com.jiniguez.demo.Service.Implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiniguez.demo.Config.CustomPageRequest;
import com.jiniguez.demo.DAO.AppointmentDAO;
import com.jiniguez.demo.DTO.AppointmentDTO;
import com.jiniguez.demo.DTO.ConsultationDTO;
import com.jiniguez.demo.DTO.PatientDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Model.Appointment;
import com.jiniguez.demo.Service.AppointmentService;
import com.jiniguez.demo.Service.ConsultationService;
import com.jiniguez.demo.Service.PatientService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	AppointmentDAO appointmentDAO;
	
	@Autowired
	PatientService patientService;

	@Autowired
	ConsultationService consultationService;
	
	@Autowired
	private DozerBeanMapper dozer;

	@Override
	public AppointmentDTO appointmentToDTO(Appointment appointment) {
		return dozer.map(appointment, AppointmentDTO.class);
	}
	
	@Override
	public Appointment DTOToAppointment(AppointmentDTO appointment) throws NotFoundException {
		
		Appointment a = Optional.ofNullable(appointmentDAO.findOne(appointment.getId())).orElse(new Appointment());
		
		a.setPosition(appointment.getPosition());
		a.setPatient(patientService.findById(appointment.getPatient_id()));
		a.setConsultation(consultationService.findById(appointment.getConsultation_id()));
		return a;
	}
	
	@Override
	public AppointmentDTO findDTOById(Integer id) throws NotFoundException {
		return appointmentToDTO(findById(id));
	}
	
	@Override
	public Appointment findById(Integer id) throws NotFoundException {
		Appointment a = Optional.ofNullable(appointmentDAO.findOne(id))
        		.orElseThrow(NotFoundException::new);
		return a;
	}
	
	@Override
	public PatientDTO findPatients(Integer id) throws NotFoundException {
		return patientService.patientToDTO(findById(id).getPatient());
	}
	
	@Override
	public ConsultationDTO findConsultations(Integer id) throws NotFoundException {
		return consultationService.consultationToDTO(findById(id).getConsultation());
	}

	@Override
	public List<AppointmentDTO> findAll(Integer page, Integer size) {
		final Iterable<Appointment> findAll = appointmentDAO.findAll(CustomPageRequest.newPageRequest(page, size));
		final List<AppointmentDTO> res = new ArrayList<>();
		findAll.forEach(b ->{
				final AppointmentDTO aDTO = appointmentToDTO(b);
				res.add(aDTO);
			});
		return res;
	}
	
	@Override
	public AppointmentDTO create(AppointmentDTO appointment) throws NotFoundException {
		final Appointment a = DTOToAppointment(appointment);
		return appointmentToDTO(appointmentDAO.save(a));
	}

	@Override
	public void update(AppointmentDTO appointment) throws NotFoundException {
		final Appointment a = DTOToAppointment(appointment);
		appointmentDAO.save(a);		
	}

	@Override
	public void delete(Integer id) {
		appointmentDAO.delete(id);
	}
}
