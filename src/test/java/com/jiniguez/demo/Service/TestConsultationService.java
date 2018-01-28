package com.jiniguez.demo.Service;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.jiniguez.demo.Config.Constants;
import com.jiniguez.demo.Config.CustomPageRequest;
import com.jiniguez.demo.DAO.ConsultationDAO;
import com.jiniguez.demo.DTO.AppointmentDTO;
import com.jiniguez.demo.DTO.ConsultationDTO;
import com.jiniguez.demo.DTO.DoctorDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Model.Appointment;
import com.jiniguez.demo.Model.Clinic;
import com.jiniguez.demo.Model.Consultation;
import com.jiniguez.demo.Model.Doctor;
import com.jiniguez.demo.Model.Patient;
import com.jiniguez.demo.Model.Room;
import com.jiniguez.demo.Model.Turn;
import com.jiniguez.demo.Service.Implementation.ConsultationServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestConsultationService {
	
	private static Clinic CLINIC = new Clinic();
	
	private static Room ROOM = new Room();
	
	private static String NAME = "NAME";
	
	private static Integer ID = 1;
	
	private static final Integer SIZE = 1;

	private static final Integer PAGE = 1;
	
	private static final Integer ROOMNUMBER = 1;
	
	private static final String EMAIL = "correito@correito.com";

	private static final String EXTERNALID = "EXTERNALID";
	
	private static Page<Consultation> ITERABLE_CONSULTATION;

	private static List<Consultation> LIST_CONSULTATION;	
	
	private static Patient PATIENT = new Patient();

	private static Doctor DOCTOR = new Doctor();
	
	private static DoctorDTO DOCTORDTO = new DoctorDTO();
	
	private static Consultation CONSULTATION = new Consultation();
	
	private static ConsultationDTO CONSULTATIONDTO = new ConsultationDTO();
	
	private static Appointment APPOINTMENT = new Appointment();

	private static AppointmentDTO APPOINTMENTDTO = new AppointmentDTO();
	
	private static final Integer POSITION = 1;

	private static Turn TURN = Turn.M;
	
	private static Date DAY;
	
	private static final Double PRICE = 0.5;


	@InjectMocks
	ConsultationService service = new ConsultationServiceImpl();
	
	@Mock
	ConsultationDAO consultationDAO;
	
	@Mock
	DoctorService doctorService;
	
	@Mock
	RoomService roomService;

	@Mock
	AppointmentService appointmentService;

	@Before
	public void init() throws ParseException {
		DAY = Constants.DATEFORMAT.parse("01-01-2000");
		CONSULTATION.setAppointments(Arrays.asList(APPOINTMENT));
		CONSULTATION.setDay(DAY);
		CONSULTATION.setDoctor(DOCTOR);
		CONSULTATION.setId(ID);
		CONSULTATION.setRoom(ROOM);
		CONSULTATION.setTurn(TURN);
		
		CONSULTATIONDTO.setDay(Constants.DATEFORMAT.format(DAY));
		CONSULTATIONDTO.setDoctor_internal_id(ID);
		CONSULTATIONDTO.setId(ID);
		CONSULTATIONDTO.setRoom_id(ID);
		CONSULTATIONDTO.setTurn(TURN);
		
		APPOINTMENT.setId(ID);
		APPOINTMENT.setPatient(PATIENT);
		APPOINTMENT.setPosition(POSITION);
		APPOINTMENT.setConsultation(CONSULTATION);
		APPOINTMENTDTO.setId(ID);
		APPOINTMENTDTO.setPatient_id(ID);
		APPOINTMENTDTO.setPosition(POSITION);
		APPOINTMENTDTO.setConsultation_id(ID);
		
		DOCTOR.setConsultations(Arrays.asList(CONSULTATION));
		DOCTOR.setEmail(EMAIL);
		DOCTOR.setId(EXTERNALID);
		DOCTOR.setInternalId(ID);
		DOCTOR.setName(NAME);
		DOCTOR.setPrice(PRICE);
		
		DOCTORDTO.setInternalId(ID);
		DOCTORDTO.setId(EXTERNALID);
		DOCTORDTO.setEmail(EMAIL);
		DOCTORDTO.setName(NAME);
		DOCTORDTO.setPrice(PRICE);
		
		ROOM.setClinic(CLINIC);
		ROOM.setConsultations(Arrays.asList(CONSULTATION));
		ROOM.setId(ID);
		ROOM.setRoomNumber(ROOMNUMBER);
		
		LIST_CONSULTATION = Arrays.asList(CONSULTATION);
		ITERABLE_CONSULTATION = new PageImpl<>(LIST_CONSULTATION);
		
	}
	
	@Test
	public void testConsultationToDTO() {
		ConsultationDTO c = service.consultationToDTO(CONSULTATION);
		assertConsultationDTO(c);
	}

	@Test
	public void DTOToConsultation() throws NotFoundException, ParseException {
		Mockito.when(doctorService.findById(ID)).thenReturn(DOCTOR);
		Mockito.when(roomService.findById(ID)).thenReturn(ROOM);
		
		Consultation c = service.DTOToConsultation(CONSULTATIONDTO);
		assertConsultation(c);
	}
	
	@Test
	public void testFindDTOByIdBase() throws NotFoundException {
		Mockito.when(consultationDAO.findOne(ID)).thenReturn(CONSULTATION);
		ConsultationDTO a = service.findDTOById(ID);
		assertConsultationDTO(a);
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindDTOByIdNotFoundException() throws NotFoundException {
		Mockito.when(consultationDAO.findOne(ID)).thenReturn(null);

		service.findDTOById(ID);
	}

	@Test
	public void testFindByIdBase() throws NotFoundException {
		Mockito.when(consultationDAO.findOne(ID)).thenReturn(CONSULTATION);
		Consultation a = service.findById(ID);
		assertConsultation(a);
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindByIdNotFoundException() throws NotFoundException {
		Mockito.when(consultationDAO.findOne(ID)).thenReturn(null);
		service.findById(ID);
	}
	
	@Test
	public void testFindAllBase() {
		Mockito.when(consultationDAO.findAll(CustomPageRequest.newPageRequest(PAGE, SIZE))).thenReturn(ITERABLE_CONSULTATION);
		List<ConsultationDTO> l = service.findAll(SIZE, PAGE);
		Assert.assertEquals(l.size(), 1);
		assertConsultationDTO(l.get(0));
	}
	
	@Test
	public void testFindAll2Base() {
		Mockito.when(consultationDAO.findAll()).thenReturn(LIST_CONSULTATION);
		List<Consultation> l = service.findAll();
		Assert.assertEquals(l.size(), 1);
		assertConsultation(l.get(0));
	}
	
	@Test
	public void testFindAppointments() throws NotFoundException {
		Mockito.when(consultationDAO.findOne(ID)).thenReturn(CONSULTATION);
		Mockito.when(appointmentService.appointmentToDTO(APPOINTMENT)).thenReturn(APPOINTMENTDTO);
		
		List<AppointmentDTO> l = service.findAppointments(ID);
		Assert.assertEquals(l.size(), 1);
		Assert.assertNotNull(l.get(0));
		Assert.assertEquals(l.get(0).getPosition(), POSITION);
		Assert.assertEquals(l.get(0).getPatient_id(), ID);
		Assert.assertEquals(l.get(0).getConsultation_id(), ID);
	}

	@Test
	public void testCreate() throws NotFoundException, ParseException {
		Mockito.when(consultationDAO.findOne(ID)).thenReturn(CONSULTATION);
		Mockito.when(consultationDAO.save(CONSULTATION)).thenReturn(CONSULTATION);
		Mockito.when(doctorService.findById(ID)).thenReturn(DOCTOR);
		Mockito.when(roomService.findById(ID)).thenReturn(ROOM);
		
		ConsultationDTO a = service.create(CONSULTATIONDTO);
		
		assertConsultationDTO(a);
	}


	@Test
	public void testUpdate() throws NotFoundException, ParseException {
		Mockito.when(consultationDAO.findOne(ID)).thenReturn(CONSULTATION);
		Mockito.when(consultationDAO.save(CONSULTATION)).thenReturn(CONSULTATION);
		
		service.update(CONSULTATIONDTO);
	}
	
	@Test
	public void testDelete() {
		service.delete(ID);
	}
	
	@Test
	public void testFindDoctor() throws NotFoundException {
		Mockito.when(consultationDAO.findOne(ID)).thenReturn(CONSULTATION);
		Mockito.when(doctorService.findDTOById(ID)).thenReturn(DOCTORDTO);
		
		DoctorDTO d = service.findDoctorByConsultaId(ID);
		
		Assert.assertNotNull(d);
		Assert.assertEquals(d.getId(), EXTERNALID);
		Assert.assertEquals(d.getInternalId(), ID);
		Assert.assertEquals(d.getName(), NAME);
		Assert.assertEquals(d.getEmail(), EMAIL);
		Assert.assertEquals(d.getPrice(), PRICE);
	}

	private void assertConsultation(Consultation c) {
		Assert.assertNotNull(c);
		Assert.assertEquals(c.getDay(),DAY);
		Assert.assertEquals(c.getDoctor(), DOCTOR);
		Assert.assertEquals(c.getId(), ID);
		Assert.assertEquals(c.getRoom(), ROOM);
		Assert.assertEquals(c.getTurn(), TURN);
	}
	
	private void assertConsultationDTO(ConsultationDTO c) {
		Assert.assertNotNull(c);
		Assert.assertEquals(c.getDay(),Constants.DATEFORMAT.format(DAY));
		Assert.assertEquals(c.getDoctor_internal_id(), ID);
		Assert.assertEquals(c.getId(), ID);
		Assert.assertEquals(c.getRoom_id(), ID);
		Assert.assertEquals(c.getTurn(), TURN);
	}

}
