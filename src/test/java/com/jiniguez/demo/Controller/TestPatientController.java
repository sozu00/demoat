package com.jiniguez.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.jiniguez.demo.DTO.AppointmentDTO;
import com.jiniguez.demo.DTO.PatientDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Service.PatientService;

@RunWith(MockitoJUnitRunner.class)
public class TestPatientController {

	private static final Integer ID = 1;
	private static final Integer IDException = -1;
	private static final Integer PAGE = 1;
	private static final Integer SIZE = 10;
	private static final String NAME = "nombre";
	private PatientDTO patientDTO = new PatientDTO();
	private PatientDTO patientDTOException = new PatientDTO();
	private AppointmentDTO appointmentDTO = new AppointmentDTO();
	private List<PatientDTO> listDTO = new ArrayList<>();
	private List<AppointmentDTO> listAppointmentDTO = new ArrayList<>();
	
	@Mock
	private PatientService patientService;
	
	@InjectMocks 
	private PatientController patientController = new PatientController();
	
	@Before
	public void initPatient() throws NotFoundException {
		patientDTO.setId(ID);
		patientDTO.setName(NAME);
		patientDTOException.setId(IDException);
		patientDTOException.setName(NAME);
		appointmentDTO.setId(ID);
		listAppointmentDTO.add(appointmentDTO);
		listDTO.add(patientDTO);
		Mockito.when(patientService.findAll(PAGE, SIZE)).thenReturn(listDTO);
		Mockito.when(patientService.findDTOById(ID)).thenReturn(patientDTO);
		Mockito.when(patientService.findDTOById(-1)).thenThrow(new NotFoundException());
		Mockito.doNothing().when(patientService).update(patientDTO);
		Mockito.doNothing().when(patientService).delete(ID);
		Mockito.when(patientService.create(patientDTO)).thenReturn(patientDTO);
		Mockito.when(patientService.findAppointments(ID)).thenReturn(listAppointmentDTO);
	}
	
	@Test
	public void testFindAll(){
		List<PatientDTO> result = patientController.findAll(PAGE, SIZE);
		
		Assert.assertEquals(result.get(0).getName(),NAME);
	}
	
	@Test(expected = NotFoundException.class)
	public void TestfindOneByIdException() throws NotFoundException {
		patientController.findOneById(-1);
	}
	
	@Test
	public void TestfindOneById() throws NotFoundException{
		PatientDTO result = patientController.findOneById(ID);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(result.getName(), NAME);
	}
	
	@Test
	public void findAppointments() throws NotFoundException {
		List<AppointmentDTO> result = patientController.findAppointments(ID);
		
		Assert.assertEquals(result.get(0).getId(), ID);
	}
	@Test
	public void TestCreate() {
		PatientDTO result = patientController.create(patientDTO);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(result.getName(), NAME);
	}
	
	@Test
	public void TestUpdate() throws NotFoundException{
		patientController.update(patientDTO, ID);
	}
	
	@Test
	public void TestDelete() {
		patientController.delete(patientDTO, ID);
	}	
}
