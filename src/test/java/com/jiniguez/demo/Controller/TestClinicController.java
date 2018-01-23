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

import com.jiniguez.demo.DTO.ClinicDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Service.ClinicService;

@RunWith(MockitoJUnitRunner.class)
public class TestClinicController {

	private static final Integer ID = 1;
	private static final Integer IDException = -1;
	private static final Integer PAGE = 1;
	private static final Integer SIZE = 10;
	private ClinicDTO clinicDTO = new ClinicDTO();
	private ClinicDTO clinicDTOException = new ClinicDTO();
	private List<ClinicDTO> listDTO = new ArrayList<>();
	
	@Mock
	private ClinicService clinicService;
	
	@InjectMocks 
	private ClinicController clinicController = new ClinicController();
	
	@Before
	public void initClinic() throws NotFoundException {
		clinicDTO.setId(ID);
		clinicDTOException.setId(IDException);
		
		listDTO.add(clinicDTO);
		Mockito.when(clinicService.findAll(PAGE, SIZE)).thenReturn(listDTO);
		Mockito.when(clinicService.findDTOById(ID)).thenReturn(clinicDTO);
		Mockito.when(clinicService.findDTOById(-1)).thenThrow(new NotFoundException());
		Mockito.doNothing().when(clinicService).update(clinicDTO);
		Mockito.doNothing().when(clinicService).delete(ID);
		Mockito.when(clinicService.create(clinicDTO)).thenReturn(clinicDTO);
	}
	
	@Test
	public void testFindAll(){
		List<ClinicDTO> result = clinicController.findAll(PAGE, SIZE);
		
		Assert.assertEquals(result.get(0).getId(),listDTO.get(0).getId());
	}
	
	@Test(expected = NotFoundException.class)
	public void TestfindOneByIdException() throws NotFoundException {
		clinicController.findOneById(-1);
	}
	
	@Test
	public void TestfindOneById() throws NotFoundException{
		ClinicDTO result = clinicController.findOneById(ID);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(result.getId(), ID);
	}
	
	@Test
	public void TestCreate() {
		ClinicDTO result = clinicController.create(clinicDTO);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(result.getId(), ID);
	}
	
	@Test
	public void TestUpdate() throws NotFoundException{
		clinicController.update(clinicDTO, ID);
	}
	
	@Test
	public void TestDelete() {
		clinicController.delete(clinicDTO, ID);
	}	
}
