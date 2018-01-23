package com.jiniguez.demo.Controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jiniguez.demo.DAO.DoctorDAO;
import com.jiniguez.demo.DTO.AppointmentDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Service.AppointmentService;
import com.jiniguez.demo.Service.DoctorService;
import com.jiniguez.demo.Service.Implementation.DoctorServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestAppointmentController {

	private static final Integer ID = 1;
	private static final Integer POSITION = 1;
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
		
		appointmentDTOException.setId(1);
		appointmentDTOException.setPosition(1);
		appointmentDTOException.setPatient_id(9999);
		
		listDTO.add(appointmentDTO);
		Mockito.when(appointmentService.findAll(PAGE, SIZE)).thenReturn(listDTO);
		Mockito.when(appointmentService.findDTOById(ID)).thenReturn(appointmentDTO);
		Mockito.when(appointmentService.findDTOById(-1)).thenThrow(new NotFoundException());
		Mockito.doNothing().when(appointmentService).update(appointmentDTO);
		Mockito.doNothing().when(appointmentService).delete(ID);
		Mockito.when(appointmentService.create(appointmentDTO)).thenReturn(appointmentDTO);
		Mockito.when(appointmentService.create(appointmentDTOException)).thenThrow(new NotFoundException());
	}
	
	@Test
	public void testFindAll(){
		List<AppointmentDTO> result = appointmentController.findAll(PAGE, SIZE);
		
		Assert.assertEquals(result.get(0).getId(),listDTO.get(0).getId());
	}
	
	@Test(expected = NotFoundException.class)
	public void TestfindOneByIdException() throws NotFoundException {
		appointmentController.findOneById(-1);
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
