package com.jiniguez.demo.Service;

import java.text.ParseException;
import java.util.ArrayList;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.jiniguez.demo.Config.Constants;
import com.jiniguez.demo.Config.CustomPageRequest;
import com.jiniguez.demo.DAO.DoctorDAO;
import com.jiniguez.demo.DTO.ConsultationDTO;
import com.jiniguez.demo.DTO.DoctorDTO;
import com.jiniguez.demo.DTO.PatientDTO;
import com.jiniguez.demo.DTO.StatisticsDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Model.Appointment;
import com.jiniguez.demo.Model.Consultation;
import com.jiniguez.demo.Model.Doctor;
import com.jiniguez.demo.Model.Patient;
import com.jiniguez.demo.Model.Turn;
import com.jiniguez.demo.Service.Implementation.DoctorServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestDoctorService {

	private static final String URL_ONE_DOCTOR = "http://doctor.dbgjerez.es:8080/api/doctor/";

	private static final String URL = "http://doctor.dbgjerez.es:8080/api/doctor?page=";

	private static final String END_DATE = "01-01-2050";

	private static final String INITIAL_DATE = "01-01-1900";

	private static final Integer ID = 1;

	private static final String EMAIL = "correito@correito.com";

	private static final String NAME = "nombresito";

	private static final String EXTERNALID = "EXTERNALID";

	private static final Double PRICE = 0.5;

	private static final Integer SIZE = 1;

	private static final Integer PAGE = 1;

	private static Doctor DOCTOR = new Doctor();
	
	private static DoctorDTO DOCTORDTO = new DoctorDTO();
	
	private static Page<Doctor> ITERABLE_DOCTOR;

	private static List<Doctor> LIST_DOCTOR;
	
	private static PatientDTO PATIENTDTO = new PatientDTO();
	
	private static Patient PATIENT = new Patient();
	
	private static Consultation CONSULTATION = new Consultation();
	
	private static ConsultationDTO CONSULTATIONDTO = new ConsultationDTO();
	
	private static Appointment APPOINTMENT = new Appointment();
	
	private static Turn TURN = Turn.M;
	
	@InjectMocks 
	private DoctorService service = new DoctorServiceImpl();

	@Mock
	DoctorDAO doctorDAO;
	
	@Mock
	private DozerBeanMapper dozer;
	
	@Mock
	private PatientService patientService;
	
	@Mock
	private ConsultationService consultationService;
	
	@Mock
	private RestTemplate restTemplate;

	@Before
	public void init() throws ParseException {
		DOCTOR.setInternalId(ID);
		DOCTOR.setId(EXTERNALID);
		DOCTOR.setEmail(EMAIL);
		DOCTOR.setName(NAME);
		DOCTOR.setPrice(PRICE);
		DOCTORDTO.setInternalId(ID);
		DOCTORDTO.setId(EXTERNALID);
		DOCTORDTO.setEmail(EMAIL);
		DOCTORDTO.setName(NAME);
		DOCTORDTO.setPrice(PRICE);
		PATIENT.setId(ID);
		PATIENT.setName(NAME);
		PATIENTDTO.setId(ID);
		PATIENTDTO.setName(NAME);
		APPOINTMENT.setId(ID);
		CONSULTATION.setId(ID);
		CONSULTATION.setTurn(TURN);
		CONSULTATION.setDay(Constants.DATEFORMAT.parse("01-01-2000"));
		CONSULTATION.setDoctor(DOCTOR);
		CONSULTATIONDTO.setId(ID);
		CONSULTATIONDTO.setTurn(TURN);
		APPOINTMENT.setPatient(PATIENT);
		APPOINTMENT.setConsultation(CONSULTATION);
		CONSULTATION.setAppointments(Arrays.asList(APPOINTMENT));
		DOCTOR.setConsultations(Arrays.asList(CONSULTATION));
		
		LIST_DOCTOR = Arrays.asList(DOCTOR);
		
		ITERABLE_DOCTOR = new PageImpl<Doctor>(LIST_DOCTOR);
		
		//Mock de dozer es necesario para todas las clases
		Mockito.when(dozer.map(DOCTOR, DoctorDTO.class)).thenReturn(DOCTORDTO);
		
	}
	

	@Test
	public void testDTOToDoctorBase() {
		Mockito.when(doctorDAO.findOne(ID)).thenReturn(DOCTOR);
		
		Doctor d = service.DTOToDoctor(DOCTORDTO);
		
		assertDoctor(d);
	}
	
	@Test
	public void testDTOToDoctorNotFoundDoctor() {
		Mockito.when(doctorDAO.findOne(ID)).thenReturn(null);
		
		Doctor d = service.DTOToDoctor(DOCTORDTO);
		
		assertDoctor(d);
	}
	
	@Test
	public void testFindDTOByIdBase() throws NotFoundException {
		Mockito.when(doctorDAO.findOne(ID)).thenReturn(DOCTOR);
		DoctorDTO d = service.findDTOById(ID);
		
		Assert.assertNotNull(d);
		assertDoctorDTO(d);
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindDTOByIdNotFoundException1() throws NotFoundException {
		Mockito.when(doctorDAO.findOne(ID)).thenReturn(null);
		
		service.findDTOById(ID);
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindDTOByIdNotFoundException2() throws NotFoundException {
		Mockito.when(doctorDAO.findOne(ID)).thenReturn(null);
		service.findDTOById(null);
	}
	
	@Test
	public void testFindByExternalIdBase() {
		Mockito.when(doctorDAO.findOneByExternalID(EXTERNALID)).thenReturn(DOCTOR);
		
		Doctor d = service.findByExternalId(EXTERNALID);
		
		assertDoctor(d);
	}
	
	@Test
	public void testFindByExternalIdNotFound() {
		Mockito.when(doctorDAO.findOneByExternalID(EXTERNALID)).thenReturn(null);
		Doctor d = service.findByExternalId(EXTERNALID);
		
		Assert.assertNull(d);
	}
	
	@Test
	public void testFindByIdBase() throws NotFoundException {
		Mockito.when(doctorDAO.findOne(ID)).thenReturn(DOCTOR);
		
		final Doctor d = service.findById(ID);

		assertDoctor(d);
	}

	@Test(expected = NotFoundException.class)
	public void testFindByIdNotFound() throws NotFoundException{
		Mockito.when(doctorDAO.findOne(ID)).thenReturn(null);
		service.findById(ID);
	}

	
	@Test
	public void testFindAllBase() {
		Mockito.when(doctorDAO.findAll(CustomPageRequest.newPageRequest(PAGE, SIZE))).thenReturn(ITERABLE_DOCTOR);
		
		List<DoctorDTO> l = service.findAll(PAGE, SIZE);

		DoctorDTO d = l.get(0);
		
		assertDoctorDTO(d);
	}

	@Test
	public void testFindAllEmptyList() {
		Mockito.when(doctorDAO.findAll(CustomPageRequest.newPageRequest(PAGE, SIZE))).thenReturn(new PageImpl<>(new ArrayList<>()));
		List<DoctorDTO> l = service.findAll(PAGE, SIZE);

		Assert.assertEquals(l.size(), 0);
	}
	
	
	@Test
	public void testCreate() {
		Mockito.when(doctorDAO.findOne(ID)).thenReturn(DOCTOR);
		Mockito.when(doctorDAO.save(DOCTOR)).thenReturn(DOCTOR);
		
		DoctorDTO d = service.create(DOCTORDTO);

		assertDoctorDTO(d);
	}


	@Test
	public void testUpdate() {
		Mockito.when(doctorDAO.findOne(ID)).thenReturn(DOCTOR);
		Mockito.when(doctorDAO.save(DOCTOR)).thenReturn(DOCTOR);
		
		service.update(DOCTORDTO);
	}
	
	@Test
	public void testDelete() {
		service.delete(ID);
	}
	
	@Test
	public void testfindByNameBase(){
		Mockito.when(doctorDAO.findAll()).thenReturn(LIST_DOCTOR);
		
		List<DoctorDTO> d = service.findByName(NAME);
		
		Assert.assertEquals(d.size(), 1);
		assertDoctorDTO(d.get(0));
	}
	
	@Test
	public void testfindByNameNotFound(){
		Mockito.when(doctorDAO.findAll()).thenReturn(LIST_DOCTOR);
		List<DoctorDTO> l = service.findByName(EXTERNALID);

		Assert.assertEquals(l.size(), 0);
	}
	
	@Test
	public void testFindNTopDoctorsBase() {
		Mockito.when(doctorDAO.findTopNDoctorsWithMorePatients()).thenReturn(Arrays.asList(DOCTOR));
		
		List<DoctorDTO> l = service.findTopNDoctorsWithMorePatients(SIZE);
		
		Assert.assertEquals(l.size(), 1);
		assertDoctorDTO(l.get(0));
	}
	
	@Test
	public void testFindPatientsBase() throws NotFoundException {
		Mockito.when(patientService.patientToDTO(PATIENT)).thenReturn(PATIENTDTO);
		Mockito.when(doctorDAO.findOne(ID)).thenReturn(DOCTOR);
		
		List<PatientDTO> l = service.findPatients(ID);
		
		Assert.assertEquals(l.size(), 1);
		Assert.assertEquals(l.get(0).getName(), NAME);
	}
	
	@Test
	public void testFindPatientsDuplicatedPatients() throws NotFoundException {
		Mockito.when(patientService.patientToDTO(PATIENT)).thenReturn(PATIENTDTO);
		Mockito.when(doctorDAO.findOne(ID)).thenReturn(DOCTOR);
		
		DOCTOR.setConsultations(Arrays.asList(CONSULTATION, CONSULTATION));
		
		List<PatientDTO> l = service.findPatients(ID);
		
		Assert.assertEquals(l.size(), 1);
		Assert.assertEquals(l.get(0).getName(), NAME);
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindPatientsException() throws NotFoundException {
		Mockito.when(doctorDAO.findOne(ID)).thenReturn(null);
		
		service.findPatients(ID);
	}
	
	@Test
	public void testFindConsultationsBase() throws NotFoundException {
		Mockito.when(consultationService.consultationToDTO(CONSULTATION)).thenReturn(CONSULTATIONDTO);
		Mockito.when(doctorDAO.findOne(ID)).thenReturn(DOCTOR);
		
		List<ConsultationDTO> l = service.findConsultations(ID);
		
		Assert.assertEquals(l.size(), 1);
		Assert.assertEquals(l.get(0).getTurn(), TURN);
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindConsultationsException() throws NotFoundException {
		Mockito.when(doctorDAO.findOne(ID)).thenReturn(null);
		
		service.findConsultations(ID);
	}
	
	@Test
	public void testGetStats() throws NotFoundException, ParseException {
		Mockito.when(consultationService.findAll()).thenReturn(Arrays.asList(CONSULTATION, CONSULTATION));
		
		List<StatisticsDTO> stats = service.getStats(INITIAL_DATE, END_DATE);
		Double TOTALPRICE = PRICE*2;
		Assert.assertEquals(stats.size(), 1);
		Assert.assertEquals(stats.get(0).getTotalPrice(), TOTALPRICE);
		Assert.assertEquals(stats.get(0).getDoctorID(), ID);
		
	}
	
	@Test(expected = ParseException.class)
	public void testGetStatsParseException() throws ParseException {
		Mockito.when(consultationService.findAll()).thenReturn(Arrays.asList(CONSULTATION));
		service.getStats("BUG", END_DATE);
	}
	
	@Test
	public void testInit() {
		Mockito.when(doctorDAO.findOneByExternalID(EXTERNALID)).thenReturn(null);
		Mockito.when(restTemplate.getForEntity(URL + 0, DoctorDTO[].class)).thenReturn(new ResponseEntity<DoctorDTO[]>(new DoctorDTO[] {DOCTORDTO}, HttpStatus.OK));
		Mockito.when(restTemplate.getForEntity(URL + 1, DoctorDTO[].class)).thenReturn(new ResponseEntity<DoctorDTO[]>(new DoctorDTO[] {}, HttpStatus.OK));
		Mockito.when(restTemplate.getForObject(URL_ONE_DOCTOR+EXTERNALID, DoctorDTO.class)).thenReturn(DOCTORDTO);
		Mockito.when(doctorDAO.save(DOCTOR)).thenReturn(DOCTOR);
		service.init();
	}
	@Test
	public void testInitFullDB() {
		Mockito.when(doctorDAO.findOneByExternalID(EXTERNALID)).thenReturn(DOCTOR);
		Mockito.when(restTemplate.getForEntity(URL + 0, DoctorDTO[].class)).thenReturn(new ResponseEntity<DoctorDTO[]>(new DoctorDTO[] {DOCTORDTO}, HttpStatus.OK));
		Mockito.when(restTemplate.getForEntity(URL + 1, DoctorDTO[].class)).thenReturn(new ResponseEntity<DoctorDTO[]>(new DoctorDTO[] {}, HttpStatus.OK));
		Mockito.when(restTemplate.getForObject(URL_ONE_DOCTOR+EXTERNALID, DoctorDTO.class)).thenReturn(DOCTORDTO);
		Mockito.when(doctorDAO.save(DOCTOR)).thenReturn(DOCTOR);
		service.init();
	}
	
	private void assertDoctorDTO(DoctorDTO d) {
		Assert.assertNotNull(d);
		Assert.assertEquals(d.getId(), EXTERNALID);
		Assert.assertEquals(d.getInternalId(), ID);
		Assert.assertEquals(d.getName(), NAME);
		Assert.assertEquals(d.getEmail(), EMAIL);
		Assert.assertEquals(d.getPrice(), PRICE);
	}


	private void assertDoctor(final Doctor d) {
		Assert.assertNotNull(d);
		Assert.assertEquals(d.getId(), EXTERNALID);
		Assert.assertEquals(d.getInternalId(), ID);
		Assert.assertEquals(d.getName(), NAME);
		Assert.assertEquals(d.getEmail(), EMAIL);
		Assert.assertEquals(d.getPrice(), PRICE);
	}
	
}
