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

import com.jiniguez.demo.DTO.DoctorDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Service.DoctorService;

@RunWith(MockitoJUnitRunner.class)
public class TestDoctorController {

	private static final Integer ID = 1;
	private static final Integer IDException = -1;
	private static final Integer PAGE = 1;
	private static final Integer SIZE = 10;
	private static final Integer NUM = 1;
	private static final String NAME = "nombre";
	private DoctorDTO doctorDTO = new DoctorDTO();
	private DoctorDTO doctorDTOException = new DoctorDTO();
	private List<DoctorDTO> listDTO = new ArrayList<>();
	
	@Mock
	private DoctorService doctorService;
	
	@InjectMocks 
	private DoctorController doctorController = new DoctorController();
	
	@Before
	public void initDoctor() throws NotFoundException {
		doctorDTO.setInternalId(ID);
		doctorDTO.setName(NAME);
		doctorDTOException.setInternalId(IDException);
		doctorDTOException.setName(NAME);
		
		listDTO.add(doctorDTO);
		Mockito.when(doctorService.findAll(PAGE, SIZE)).thenReturn(listDTO);
		Mockito.when(doctorService.findDTOById(ID)).thenReturn(doctorDTO);
		Mockito.when(doctorService.findDTOById(-1)).thenThrow(new NotFoundException());
		Mockito.doNothing().when(doctorService).update(doctorDTO);
		Mockito.doNothing().when(doctorService).delete(ID);
		Mockito.when(doctorService.create(doctorDTO)).thenReturn(doctorDTO);
		Mockito.when(doctorService.findTopNDoctorsWithMorePatients(NUM)).thenReturn(listDTO);
		Mockito.when(doctorService.findByName(NAME)).thenReturn(listDTO);
	}
	
	@Test
	public void testFindAll(){
		List<DoctorDTO> result = doctorController.findAll(PAGE, SIZE);
		
		Assert.assertEquals(result.get(0).getName(),NAME);
	}
	
	@Test(expected = NotFoundException.class)
	public void TestfindOneByIdException() throws NotFoundException {
		doctorController.findOneById(-1);
	}
	
	@Test
	public void TestTopDoctors() {
		List<DoctorDTO> result = doctorController.findTopNDoctorsWithMorePatients(NUM);
		
		Assert.assertEquals(result.get(0).getName(), NAME);
	}
	
	@Test
	public void TestfindOneById() throws NotFoundException{
		DoctorDTO result = doctorController.findOneById(ID);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(result.getName(), NAME);
	}
	
	@Test
	public void TestfindByName() {
		List<DoctorDTO> result = doctorController.findByName(NAME);
		
		Assert.assertEquals(result.get(0).getInternalId(), ID);
	}
	
	@Test
	public void TestCreate() {
		DoctorDTO result = doctorController.create(doctorDTO);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(result.getName(), NAME);
	}
	
	@Test
	public void TestUpdate() throws NotFoundException{
		doctorController.update(doctorDTO, ID);
	}
	
	@Test
	public void TestDelete() {
		doctorController.delete(doctorDTO, ID);
	}	
}
