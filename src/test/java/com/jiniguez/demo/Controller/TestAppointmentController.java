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
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Service.AppointmentService;

@RunWith(MockitoJUnitRunner.class)
public class TestAppointmentController {

	
	/*
	 * Cambiar tests a casoSS base y casoSS excepcionales
	 * 
	 */
	private static final Integer ID = 1;
	private static final Integer POSITION = 1;
	private static final Integer IDEXCEPTION = 999;
	private static Integer PAGE = 1;
	private static Integer SIZE = 10;
	private AppointmentDTO appointmentDTO = new AppointmentDTO();
	private AppointmentDTO appointmentDTOException = new AppointmentDTO();
	private List<AppointmentDTO> listDTO = new ArrayList<>();
	@Mock
	private AppointmentService appointmentService;
	
	@InjectMocks 
	private AppointmentController appointmentController = new AppointmentController();
	
	@Before
	public void init() throws NotFoundException {
		appointmentDTO.setId(ID);
		appointmentDTO.setPosition(POSITION);
		appointmentDTO.setPatient_id(ID);
		appointmentDTO.setConsultation_id(ID);
		
		appointmentDTOException.setId(ID);
		appointmentDTOException.setPosition(ID);
		appointmentDTOException.setPatient_id(IDEXCEPTION);
		
		listDTO.add(appointmentDTO);
		Mockito.when(appointmentService.findAll(PAGE, SIZE)).thenReturn(listDTO);
		Mockito.when(appointmentService.findDTOById(ID)).thenReturn(appointmentDTO);
		Mockito.when(appointmentService.findDTOById(IDEXCEPTION)).thenThrow(new NotFoundException());
		Mockito.doNothing().when(appointmentService).update(appointmentDTO);
		Mockito.doNothing().when(appointmentService).delete(ID);
		Mockito.when(appointmentService.create(appointmentDTO)).thenReturn(appointmentDTO);
		Mockito.when(appointmentService.create(appointmentDTOException)).thenThrow(new NotFoundException());
	}
	
	@Test
	public void testFindAll(){
		List<AppointmentDTO> result = appointmentController.findAll(PAGE, SIZE);
		
		Assert.assertEquals(result.get(0).getId(),ID);
	}
	
	@Test(expected = NotFoundException.class)
	public void TestfindOneByIdException() throws NotFoundException {
		appointmentController.findOneById(IDEXCEPTION);
	}
	
	@Test
	public void TestfindOneById() throws NotFoundException{
		AppointmentDTO result = appointmentController.findOneById(ID);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(result.getPosition(), POSITION);
	}
	
	@Test
	public void TestCreate() throws NotFoundException {
		AppointmentDTO result = appointmentController.create(appointmentDTO);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(result.getPosition(), POSITION);
	}
	
	@Test(expected = NotFoundException.class)
	public void TestCreateException() throws NotFoundException {
		appointmentController.create(appointmentDTOException);
	}
	
	@Test
	public void TestUpdate() throws NotFoundException{
		appointmentController.update(appointmentDTO, ID);
	}
	
	@Test
	public void TestDelete() {
		appointmentController.delete(appointmentDTO, ID);
	}
}
