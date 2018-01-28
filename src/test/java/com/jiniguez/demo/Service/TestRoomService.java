package com.jiniguez.demo.Service;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
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
import com.jiniguez.demo.DAO.RoomDAO;
import com.jiniguez.demo.DTO.AppointmentDTO;
import com.jiniguez.demo.DTO.ConsultationDTO;
import com.jiniguez.demo.DTO.DoctorDTO;
import com.jiniguez.demo.DTO.PatientDTO;
import com.jiniguez.demo.DTO.RoomDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Model.Appointment;
import com.jiniguez.demo.Model.Clinic;
import com.jiniguez.demo.Model.Consultation;
import com.jiniguez.demo.Model.Doctor;
import com.jiniguez.demo.Model.Patient;
import com.jiniguez.demo.Model.Room;
import com.jiniguez.demo.Model.Turn;
import com.jiniguez.demo.Service.Implementation.RoomServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestRoomService {
	
	private static Clinic CLINIC = new Clinic();
	
	private static Room ROOM = new Room();
	
	private static RoomDTO ROOMDTO = new RoomDTO();
	
	private static String NAME = "NAME";
	
	private static Integer ID = 1;
	
	private static final Integer SIZE = 1;

	private static final Integer PAGE = 1;
	
	private static final Integer ROOMNUMBER = 1;
	
	private static final String EMAIL = "correito@correito.com";

	private static final String EXTERNALID = "EXTERNALID";
	
	private static Page<Room> ITERABLE_ROOM;

	private static List<Room> LIST_ROOM;	
	
	private static PatientDTO PATIENTDTO = new PatientDTO();
	
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
	private RoomService service = new RoomServiceImpl();
	
	@Mock
	private RoomDAO roomDAO;
	
	@Mock
	private ClinicService clinicService;

	@Mock
	private DozerBeanMapper dozer;

	
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
		
		PATIENT.setId(ID);
		PATIENT.setName(NAME);
		PATIENT.setAppointments(Arrays.asList(APPOINTMENT));
		
		PATIENTDTO.setId(ID);
		PATIENTDTO.setName(NAME);
		
		ROOM.setClinic(CLINIC);
		ROOM.setConsultations(Arrays.asList(CONSULTATION));
		ROOM.setId(ID);
		ROOM.setRoomNumber(ROOMNUMBER);
		ROOMDTO.setClinic_id(ID);
		ROOMDTO.setId(ID);
		ROOMDTO.setRoomNumber(ROOMNUMBER);
		
		LIST_ROOM = Arrays.asList(ROOM);
		ITERABLE_ROOM = new PageImpl<>(LIST_ROOM);
		Mockito.when(dozer.map(ROOM, RoomDTO.class)).thenReturn(ROOMDTO);
	}
	
	@Test
	public void testRoomToDTO() {
		RoomDTO c = service.roomToDTO(ROOM);
		assertRoomDTO(c);
	}

	@Test
	public void DTOToRoom() throws NotFoundException, ParseException {
		Mockito.when(clinicService.findById(ID)).thenReturn(CLINIC);
		
		Room c = service.DTOToRoom(ROOMDTO);
		assertRoom(c);
	}
	
	@Test
	public void testFindDTOByIdBase() throws NotFoundException {
		Mockito.when(roomDAO.findOne(ID)).thenReturn(ROOM);
		RoomDTO a = service.findDTOById(ID);
		assertRoomDTO(a);
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindDTOByIdNotFoundException() throws NotFoundException {
		Mockito.when(roomDAO.findOne(ID)).thenReturn(null);

		service.findDTOById(ID);
	}

	@Test
	public void testFindByIdBase() throws NotFoundException {
		Mockito.when(roomDAO.findOne(ID)).thenReturn(ROOM);
		Room a = service.findById(ID);
		assertRoom(a);
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindByIdNotFoundException() throws NotFoundException {
		Mockito.when(roomDAO.findOne(ID)).thenReturn(null);
		service.findById(ID);
	}
	
	@Test
	public void testFindAllBase() {
		Mockito.when(roomDAO.findAll(CustomPageRequest.newPageRequest(PAGE, SIZE))).thenReturn(ITERABLE_ROOM);
		List<RoomDTO> l = service.findAll(SIZE, PAGE);
		Assert.assertEquals(l.size(), 1);
		assertRoomDTO(l.get(0));
	}

	@Test
	public void testCreate() throws NotFoundException, ParseException {
		Mockito.when(roomDAO.findOne(ID)).thenReturn(ROOM);
		Mockito.when(roomDAO.save(ROOM)).thenReturn(ROOM);
		Mockito.when(clinicService.findById(ID)).thenReturn(CLINIC);
		
		RoomDTO a = service.create(ROOMDTO);
		
		assertRoomDTO(a);
	}


	@Test
	public void testUpdate() throws NotFoundException, ParseException {
		Mockito.when(roomDAO.findOne(ID)).thenReturn(ROOM);
		Mockito.when(roomDAO.save(ROOM)).thenReturn(ROOM);
		Mockito.when(clinicService.findById(ID)).thenReturn(CLINIC);
		
		service.update(ROOMDTO);
	}
	
	@Test
	public void testDelete() {
		service.delete(ID);
	}

	private void assertRoom(Room p) {
		Assert.assertNotNull(p);
		Assert.assertEquals(p.getClinic(), CLINIC);
		Assert.assertEquals(p.getRoomNumber(), ROOMNUMBER);
		Assert.assertEquals(p.getId(), ID);
	}
	
	private void assertRoomDTO(RoomDTO p) {
		Assert.assertNotNull(p);
		Assert.assertEquals(p.getClinic_id(), ID);
		Assert.assertEquals(p.getId(), ID);
		Assert.assertEquals(p.getRoomNumber(), ROOMNUMBER);
	}
}
