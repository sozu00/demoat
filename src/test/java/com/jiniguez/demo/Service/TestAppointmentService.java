package com.jiniguez.demo.Service;

import java.text.ParseException;
import java.util.Arrays;
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
import com.jiniguez.demo.DAO.AppointmentDAO;
import com.jiniguez.demo.DTO.AppointmentDTO;
import com.jiniguez.demo.DTO.ConsultationDTO;
import com.jiniguez.demo.DTO.PatientDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Model.Appointment;
import com.jiniguez.demo.Model.Consultation;
import com.jiniguez.demo.Model.Doctor;
import com.jiniguez.demo.Model.Patient;
import com.jiniguez.demo.Model.Turn;
import com.jiniguez.demo.Service.Implementation.AppointmentServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class TestAppointmentService {

	private static String NAME = "NAME";
	
	private static Integer ID = 1;
	
	private static final Integer SIZE = 1;

	private static final Integer PAGE = 1;
	
	private static final Integer POSITION = 1;

	private static Page<Appointment> ITERABLE_APPOINTMENT;

	private static List<Appointment> LIST_APPOINTMENT;	
	
	private static PatientDTO PATIENTDTO = new PatientDTO();
	
	private static Patient PATIENT = new Patient();

	private static Doctor DOCTOR = new Doctor();
	
	private static Consultation CONSULTATION = new Consultation();
	
	private static ConsultationDTO CONSULTATIONDTO = new ConsultationDTO();
	
	private static Appointment APPOINTMENT = new Appointment();
	
	private static AppointmentDTO APPOINTMENTDTO = new AppointmentDTO();

	private static Turn TURN = Turn.M;

	@InjectMocks
	AppointmentService service = new AppointmentServiceImpl();
	
	@Mock
	AppointmentDAO appointmentDAO;

	@Mock
	private PatientService patientService;
	
	@Mock
	private ConsultationService consultationService;

	@Mock
	private RoomService roomService;
	
	@Mock
	private DozerBeanMapper dozer;

	
	@Before
	public void init() throws ParseException {
		
		APPOINTMENT.setId(ID);
		APPOINTMENT.setPatient(PATIENT);
		APPOINTMENT.setPosition(POSITION);
		APPOINTMENT.setConsultation(CONSULTATION);
		APPOINTMENTDTO.setId(ID);
		APPOINTMENTDTO.setPatient_id(ID);
		APPOINTMENTDTO.setPosition(POSITION);
		APPOINTMENTDTO.setConsultation_id(ID);
		PATIENT.setId(ID);
		PATIENT.setName(NAME);
		PATIENTDTO.setId(ID);
		PATIENTDTO.setName(NAME);
		CONSULTATION.setId(ID);
		CONSULTATION.setTurn(TURN);
		CONSULTATION.setDay(Constants.DATEFORMAT.parse("01-01-2000"));
		CONSULTATIONDTO.setId(ID);
		CONSULTATIONDTO.setTurn(TURN);
		CONSULTATION.setDoctor(DOCTOR);
		CONSULTATION.setAppointments(Arrays.asList(APPOINTMENT));
		LIST_APPOINTMENT = Arrays.asList(APPOINTMENT);
		ITERABLE_APPOINTMENT = new PageImpl<>(LIST_APPOINTMENT);
		Mockito.when(dozer.map(APPOINTMENT, AppointmentDTO.class)).thenReturn(APPOINTMENTDTO);
	}
	

	@Test
	public void testAppointmentToDTO() {
		AppointmentDTO a = service.appointmentToDTO(APPOINTMENT);
		
		assertAppointmentDTO(a);
	}

	@Test
	public void testDTOToAppointment() throws NotFoundException {
		Mockito.when(consultationService.findById(ID)).thenReturn(CONSULTATION);
		Mockito.when(patientService.findById(ID)).thenReturn(PATIENT);
		Mockito.when(appointmentDAO.findOne(ID)).thenReturn(APPOINTMENT);
		
		Appointment a = service.DTOToAppointment(APPOINTMENTDTO);
		
		assertAppointment(a);
	}
	
	@Test
	public void testDTOToAppointmentNotFound() throws NotFoundException {
		Mockito.when(consultationService.findById(ID)).thenReturn(CONSULTATION);
		Mockito.when(patientService.findById(ID)).thenReturn(PATIENT);
		Mockito.when(appointmentDAO.findOne(ID)).thenReturn(null);
		
		Appointment a = service.DTOToAppointment(APPOINTMENTDTO);
		
		assertAppointment(a);
	}

	@Test
	public void testFindDTOByIdBase() throws NotFoundException {
		Mockito.when(appointmentDAO.findOne(ID)).thenReturn(APPOINTMENT);

		AppointmentDTO a = service.findDTOById(ID);
		
		assertAppointmentDTO(a);
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindDTOByIdNotFoundException() throws NotFoundException {
		Mockito.when(appointmentDAO.findOne(ID)).thenReturn(null);

		service.findDTOById(ID);
	}

	@Test
	public void testFindByIdBase() throws NotFoundException {
		Mockito.when(appointmentDAO.findOne(ID)).thenReturn(APPOINTMENT);

		Appointment a = service.findById(ID);
		assertAppointment(a);
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindByIdNotFoundException() throws NotFoundException {
		Mockito.when(appointmentDAO.findOne(ID)).thenReturn(null);

		service.findById(ID);
	}

	
	@Test
	public void testFindAllBase() {
		Mockito.when(appointmentDAO.findAll(CustomPageRequest.newPageRequest(PAGE, SIZE))).thenReturn(ITERABLE_APPOINTMENT);
		
		List<AppointmentDTO> l = service.findAll(SIZE, PAGE);
		
		Assert.assertEquals(l.size(), 1);
		assertAppointmentDTO(l.get(0));
	}
	
	@Test
	public void testFindPatientsBase() throws NotFoundException {
		Mockito.when(patientService.patientToDTO(PATIENT)).thenReturn(PATIENTDTO);
		Mockito.when(appointmentDAO.findOne(ID)).thenReturn(APPOINTMENT);
		
		PatientDTO p = service.findPatients(ID);
		
		Assert.assertEquals(p.getName(), NAME);
	}

	@Test
	public void testFindConsultationsBase() throws NotFoundException {
		Mockito.when(consultationService.consultationToDTO(CONSULTATION)).thenReturn(CONSULTATIONDTO);
		Mockito.when(appointmentDAO.findOne(ID)).thenReturn(APPOINTMENT);
		
		ConsultationDTO c = service.findConsultations(ID);
		
		Assert.assertEquals(c.getTurn(), TURN);
	}	
	
	@Test
	public void testCreate() throws NotFoundException {
		Mockito.when(appointmentDAO.findOne(ID)).thenReturn(APPOINTMENT);
		Mockito.when(appointmentDAO.save(APPOINTMENT)).thenReturn(APPOINTMENT);
		
		AppointmentDTO a = service.create(APPOINTMENTDTO);
		
		assertAppointmentDTO(a);
	}


	@Test
	public void testUpdate() throws NotFoundException {
		Mockito.when(appointmentDAO.findOne(ID)).thenReturn(APPOINTMENT);
		Mockito.when(appointmentDAO.save(APPOINTMENT)).thenReturn(APPOINTMENT);
		
		service.update(APPOINTMENTDTO);
	}
	
	@Test
	public void testDelete() {
		service.delete(ID);
	}

	
	private void assertAppointment(Appointment a) {
		Assert.assertNotNull(a);
		Assert.assertEquals(a.getPosition(), POSITION);
		Assert.assertEquals(a.getPatient().getName(), NAME);
		Assert.assertEquals(a.getConsultation().getTurn(), TURN);
	}
	
	private void assertAppointmentDTO(AppointmentDTO a) {
		Assert.assertNotNull(a);
		Assert.assertEquals(a.getPosition(), POSITION);
		Assert.assertEquals(a.getPatient_id(), ID);
		Assert.assertEquals(a.getConsultation_id(), ID);
	}

}
