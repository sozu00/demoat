package com.jiniguez.demo.Service.Implementation;

	import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiniguez.demo.Config.CustomPageRequest;
import com.jiniguez.demo.DAO.ConsultationDAO;
import com.jiniguez.demo.DTO.AppointmentDTO;
import com.jiniguez.demo.DTO.ConsultationDTO;
import com.jiniguez.demo.DTO.DoctorDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Model.Consultation;
import com.jiniguez.demo.Service.AppointmentService;
import com.jiniguez.demo.Service.ConsultationService;
import com.jiniguez.demo.Service.DoctorService;
import com.jiniguez.demo.Service.RoomService;

@Service
public class ConsultationServiceImpl implements ConsultationService {

	@Autowired
	ConsultationDAO consultationDAO;
	
	@Autowired
	DoctorService doctorService;
	
	@Autowired
	RoomService roomService;

	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private DozerBeanMapper dozer;

	@Override
	public ConsultationDTO consultationToDTO(Consultation consultation) {
		return dozer.map(consultation, ConsultationDTO.class);
	}
	
	@Override
	public Consultation DTOToConsultation(ConsultationDTO consultation) throws NotFoundException {
		Consultation c = Optional.ofNullable(consultationDAO.findOne(consultation.getId())).orElse(new Consultation());
		
		c.setDay(new Date(Long.parseLong(consultation.getDay())));
		c.setTurn(consultation.getTurn());
		c.setDoctor(doctorService.findById(consultation.getDoctor_id()));
		c.setRoom(roomService.findById(consultation.getRoom_id()));
		return c;
	}
	
	@Override
	public ConsultationDTO findDTOById(Integer id) throws NotFoundException {
		return consultationToDTO(findById(id));
	}
	
	@Override
	public Consultation findById(Integer id) throws NotFoundException {
		return Optional.ofNullable(consultationDAO.findOne(id))
        		.orElseThrow(() -> new NotFoundException());
	}

	@Override
	public List<AppointmentDTO> findAppointments(Integer id) throws NotFoundException {
		List<AppointmentDTO> apps = new ArrayList<>();
		findById(id).getAppointments().forEach(a->{apps.add(appointmentService.appointmentToDTO(a));});
		
		return apps;
	}
	
	@Override
	public List<ConsultationDTO> findAll(Integer page, Integer size) {
		final Iterable<Consultation> findAll = consultationDAO.findAll(CustomPageRequest.newPageRequest(page, size));
		final List<ConsultationDTO> res = new ArrayList<>();
		findAll.forEach(b ->{
				final ConsultationDTO aDTO = consultationToDTO(b);
				res.add(aDTO);
			});
		return res;
	}
	
	@Override
	public ConsultationDTO create(ConsultationDTO consultation) throws  NotFoundException {
		final Consultation a = DTOToConsultation(consultation);
		return consultationToDTO(consultationDAO.save(a));
	}

	@Override
	public void update(ConsultationDTO consultation) throws  NotFoundException {
		final Consultation a = DTOToConsultation(consultation);
		consultationDAO.save(a);		
	}

	@Override
	public void delete(Integer id) {
		consultationDAO.delete(id);
	}

	@Override
	public DoctorDTO findDoctorByConsultaId(Integer id) throws NotFoundException {
		ConsultationDTO aDTO = consultationToDTO(consultationDAO.findOne(id));
		return doctorService.findDTOById(aDTO.getDoctor_id());
	}
}
