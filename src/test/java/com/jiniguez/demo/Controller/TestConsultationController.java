package com.jiniguez.demo.Controller;

import java.text.ParseException;
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

import com.jiniguez.demo.DTO.ConsultationDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Model.Turn;
import com.jiniguez.demo.Service.ConsultationService;

@RunWith(MockitoJUnitRunner.class)
public class TestConsultationController {

	private static final Integer ID = 1;
	private static final Turn T = Turn.M;
	private static Integer PAGE = 1;
	private static Integer SIZE = 10;
	private ConsultationDTO consultationDTO = new ConsultationDTO();
	private ConsultationDTO consultationDTOException = new ConsultationDTO();
	private List<ConsultationDTO> listDTO = new ArrayList<>();
	@Mock
	private ConsultationService consultationService;
	
	@InjectMocks 
	private ConsultationController consultationController = new ConsultationController();
	
	@Before
	public void init() throws NotFoundException, ParseException {
		consultationDTO.setId(ID);
		consultationDTO.setDoctor_internal_id(ID);
		consultationDTO.setRoom_id(ID);
		consultationDTO.setTurn(T);
		
		consultationDTOException.setId(1);
		consultationDTOException.setDoctor_internal_id(-1);
		
		listDTO.add(consultationDTO);
		Mockito.when(consultationService.findAll(PAGE, SIZE)).thenReturn(listDTO);
		Mockito.when(consultationService.findDTOById(ID)).thenReturn(consultationDTO);
		Mockito.when(consultationService.findDTOById(-1)).thenThrow(new NotFoundException());
		Mockito.doNothing().when(consultationService).update(consultationDTO);
		Mockito.doNothing().when(consultationService).delete(ID);
		Mockito.when(consultationService.create(consultationDTO)).thenReturn(consultationDTO);
		Mockito.when(consultationService.create(consultationDTOException)).thenThrow(new NotFoundException());
	}
	
	@Test
	public void testFindAll(){
		List<ConsultationDTO> result = consultationController.findAll(PAGE, SIZE);
		
		Assert.assertEquals(result.get(0).getId(),listDTO.get(0).getId());
	}
	
	@Test(expected = NotFoundException.class)
	public void TestfindOneByIdException() throws NotFoundException {
		consultationController.findOneById(-1);
	}
	
	@Test
	public void TestfindOneById() throws NotFoundException{
		ConsultationDTO result = consultationController.findOneById(ID);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(result.getId(),ID);
	}
	
	@Test
	public void TestCreate() throws NotFoundException {
		ConsultationDTO result = consultationController.create(consultationDTO);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(result.getId(), ID);
	}
	
	@Test(expected = NotFoundException.class)
	public void TestCreateException() throws NotFoundException {
		consultationController.create(consultationDTOException);
	}
	
	@Test
	public void TestUpdate() throws NotFoundException{
		consultationController.update(consultationDTO, ID);
	}
	
	@Test
	public void TestDelete() {
		consultationController.delete(consultationDTO, ID);
	}
}
