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
import com.jiniguez.demo.DAO.ClinicDAO;
import com.jiniguez.demo.DTO.ClinicDTO;
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
import com.jiniguez.demo.Service.Implementation.ClinicServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestClinicService {

	private static Clinic CLINIC = new Clinic();
	
	private static ClinicDTO CLINICDTO = new ClinicDTO();

	private static Room ROOM = new Room();
	
	private static RoomDTO ROOMDTO = new RoomDTO();
	
	private static String NAME = "NAME";
	
	private static Integer ID = 1;
	
	private static final Integer SIZE = 1;

	private static final Integer PAGE = 1;
	
	private static final Integer ROOMNUMBER = 1;
	
	private static Page<Clinic> ITERABLE_CLINIC;

	private static List<Clinic> LIST_CLINIC;	
	
	private static PatientDTO PATIENTDTO = new PatientDTO();
	
	private static Patient PATIENT = new Patient();

	private static Doctor DOCTOR = new Doctor();
	
	private static DoctorDTO DOCTORDTO = new DoctorDTO();
	
	private static Consultation CONSULTATION = new Consultation();
	
	private static ConsultationDTO CONSULTATIONDTO = new ConsultationDTO();
	
	private static Appointment APPOINTMENT = new Appointment();
	
	private static Turn TURN = Turn.M;

	@InjectMocks
	ClinicService service = new ClinicServiceImpl();
	
	@Mock
	ClinicDAO clinicDAO;

	@Mock
	private PatientService patientService;
	
	@Mock
	private DoctorService doctorService;

	@Mock
	private RoomService roomService;
	
	@Mock
	private DozerBeanMapper dozer;

	
	@Before
	public void init() throws ParseException {
		
		CLINIC.setName(NAME);
		CLINIC.setId(ID);
		CLINICDTO.setName(NAME);
		CLINICDTO.setId(ID);
		PATIENT.setId(ID);
		PATIENT.setName(NAME);
		PATIENTDTO.setId(ID);
		PATIENTDTO.setName(NAME);
		DOCTOR.setInternalId(ID);
		DOCTOR.setName(NAME);
		DOCTORDTO.setInternalId(ID);
		DOCTORDTO.setName(NAME);
		APPOINTMENT.setId(ID);
		CONSULTATION.setId(ID);
		CONSULTATION.setTurn(TURN);
		CONSULTATION.setDay(Constants.DATEFORMAT.parse("01-01-2000"));
		CONSULTATIONDTO.setId(ID);
		CONSULTATIONDTO.setTurn(TURN);
		ROOM.setId(ID);
		ROOM.setRoomNumber(ROOMNUMBER);
		ROOMDTO.setId(ID);
		ROOMDTO.setRoomNumber(ROOMNUMBER);
		LIST_CLINIC = Arrays.asList(CLINIC);
		ITERABLE_CLINIC = new PageImpl<>(LIST_CLINIC);
		CLINIC.setRooms(Arrays.asList(ROOM));
		DOCTOR.setConsultations(Arrays.asList(CONSULTATION));
		APPOINTMENT.setPatient(PATIENT);
		APPOINTMENT.setConsultation(CONSULTATION);
		CONSULTATION.setDoctor(DOCTOR);
		CONSULTATION.setAppointments(Arrays.asList(APPOINTMENT));
		ROOM.setConsultations(Arrays.asList(CONSULTATION));
		
		Mockito.when(dozer.map(CLINIC, ClinicDTO.class)).thenReturn(CLINICDTO);
	}
	
	@Test
	public void testClinicToDTOBase() {
		
		ClinicDTO c = service.clinicToDTO(CLINIC);
		
		Assert.assertEquals(c.getName(), NAME);
	}
	
	@Test
	public void testDTOToClinicBase() {
		Mockito.when(clinicDAO.findOne(ID)).thenReturn(CLINIC);
		
		Clinic c = service.DTOToClinic(CLINICDTO);
		Assert.assertEquals(c.getName(), NAME);
		Assert.assertEquals(c.getRooms().get(0).getId(), ID);
	}
	
	@Test
	public void testDTOToClinicNotFound() {
		Mockito.when(clinicDAO.findOne(ID)).thenReturn(null);
		
		Clinic c = service.DTOToClinic(CLINICDTO);
		Assert.assertEquals(c.getName(), NAME);
		Assert.assertEquals(c.getRooms().size(), 0);
	}
	
	@Test
	public void testFindDTOByIdBase() throws NotFoundException {
		Mockito.when(clinicDAO.findOne(ID)).thenReturn(CLINIC);

		ClinicDTO c = service.findDTOById(ID);
		
		Assert.assertEquals(c.getName(), NAME);
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindDTOByIdNotFoundException() throws NotFoundException {
		Mockito.when(clinicDAO.findOne(ID)).thenReturn(null);

		service.findDTOById(ID);
	}

	@Test
	public void testFindByIdBase() throws NotFoundException {
		Mockito.when(clinicDAO.findOne(ID)).thenReturn(CLINIC);

		Clinic c = service.findById(ID);
		
		Assert.assertEquals(c.getName(), NAME);
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindByIdNotFoundException() throws NotFoundException {
		Mockito.when(clinicDAO.findOne(ID)).thenReturn(null);

		service.findById(ID);
	}

	
	@Test
	public void testFindAllBase() {
		Mockito.when(clinicDAO.findAll(CustomPageRequest.newPageRequest(PAGE, SIZE))).thenReturn(ITERABLE_CLINIC);
		
		List<ClinicDTO> l = service.findAll(SIZE, PAGE);
		
		Assert.assertEquals(l.size(), 1);
		Assert.assertEquals(l.get(0).getName(), NAME);
	}

	@Test
	public void testFindPatientsBase() throws NotFoundException {
		Mockito.when(patientService.patientToDTO(PATIENT)).thenReturn(PATIENTDTO);
		Mockito.when(clinicDAO.findOne(ID)).thenReturn(CLINIC);
		
		List<PatientDTO> l = service.findPatients(ID);
		
		Assert.assertEquals(l.size(), 1);
		Assert.assertEquals(l.get(0).getName(), NAME);
	}
	
	@Test
	public void testFindPatientsDuplicatedPatients() throws NotFoundException {
		Mockito.when(patientService.patientToDTO(PATIENT)).thenReturn(PATIENTDTO);
		Mockito.when(clinicDAO.findOne(ID)).thenReturn(CLINIC);
		
		CLINIC.setRooms(Arrays.asList(ROOM, ROOM));
		
		List<PatientDTO> l = service.findPatients(ID);
		
		Assert.assertEquals(l.size(), 1);
		Assert.assertEquals(l.get(0).getName(), NAME);
	}

	@Test
	public void testFindDoctorsBase() throws NotFoundException {
		Mockito.when(clinicDAO.findOne(ID)).thenReturn(CLINIC);
		Mockito.when(doctorService.doctorToDTO(DOCTOR)).thenReturn(DOCTORDTO);

		List<DoctorDTO> l = service.findDoctors(ID);
		
		Assert.assertEquals(l.size(), 1);
		Assert.assertEquals(l.get(0).getName(), NAME);
	}
	
	@Test
	public void testFindDoctorsDuplicated() throws NotFoundException {
		Mockito.when(clinicDAO.findOne(ID)).thenReturn(CLINIC);
		Mockito.when(doctorService.doctorToDTO(DOCTOR)).thenReturn(DOCTORDTO);
		
		CLINIC.setRooms(Arrays.asList(ROOM, ROOM));
		
		List<DoctorDTO> l = service.findDoctors(ID);
		
		Assert.assertEquals(l.size(), 1);
		Assert.assertEquals(l.get(0).getName(), NAME);
	}

	@Test
	public void testFindRooms() throws NotFoundException {
		Mockito.when(clinicDAO.findOne(ID)).thenReturn(CLINIC);
		Mockito.when(roomService.roomToDTO(ROOM)).thenReturn(ROOMDTO);
		
		List<RoomDTO> l = service.findRooms(ID);
		
		Assert.assertEquals(l.size(), 1);
		Assert.assertEquals(l.get(0).getRoomNumber(), ROOMNUMBER);
	}
	

	@Test
	public void testCreate() {
		Mockito.when(clinicDAO.findOne(ID)).thenReturn(CLINIC);
		Mockito.when(clinicDAO.save(CLINIC)).thenReturn(CLINIC);
		
		ClinicDTO c = service.create(CLINICDTO);
		
		Assert.assertNotNull(c);
		Assert.assertEquals(c.getName(), NAME);
	}


	@Test
	public void testUpdate() {
		Mockito.when(clinicDAO.findOne(ID)).thenReturn(CLINIC);
		Mockito.when(clinicDAO.save(CLINIC)).thenReturn(CLINIC);
		
		service.update(CLINICDTO);
	}
	
	@Test
	public void testDelete() {
		service.delete(ID);
	}

}
