package com.jiniguez.demo.Service.Implementation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiniguez.demo.Config.Constants;
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

	@Override
	public ConsultationDTO consultationToDTO(Consultation consultation) {
		ConsultationDTO c = new ConsultationDTO();

		c.setDay(Constants.DATEFORMAT.format(consultation.getDay()));
		c.setTurn(consultation.getTurn());
		c.setDoctor_internal_id(consultation.getDoctor().getInternalId());
		c.setId(consultation.getId());
		c.setRoom_id(consultation.getRoom().getId());
		
		return c;
	}
	
	@Override
	public Consultation DTOToConsultation(ConsultationDTO consultation) throws NotFoundException, ParseException {
		Consultation c = consultationDAO.findOne(Optional.ofNullable(consultation.getId()).orElse(Constants.NOT_FINDABLE_ID));
		
		if(c == null)
			c = new Consultation();

		c.setDay(Constants.DATEFORMAT.parse(consultation.getDay()));
		c.setTurn(consultation.getTurn());
		c.setDoctor(doctorService.findById(consultation.getDoctor_internal_id()));
		c.setId(consultation.getId());
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
	public ConsultationDTO create(ConsultationDTO consultation) throws  NotFoundException, ParseException {
		final Consultation a = DTOToConsultation(consultation);
		return consultationToDTO(consultationDAO.save(a));
	}

	@Override
	public void update(ConsultationDTO consultation) throws  NotFoundException, ParseException {
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
		return doctorService.findDTOById(aDTO.getDoctor_internal_id());
	}

	@Override
	public List<Consultation> findAll() {
		final Iterable<Consultation> findAll = consultationDAO.findAll();
		List<Consultation> list = new ArrayList<>();
		findAll.forEach(list::add);
		return list;
	}
}
