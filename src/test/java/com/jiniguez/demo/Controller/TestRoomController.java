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

import com.jiniguez.demo.DTO.RoomDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Service.RoomService;

@RunWith(MockitoJUnitRunner.class)
public class TestRoomController {

	private static final Integer ID = 1;
	private static final Integer IDException = -1;
	private static final Integer PAGE = 1;
	private static final Integer SIZE = 10;
	private RoomDTO roomDTO = new RoomDTO();
	private RoomDTO roomDTOException = new RoomDTO();
	private List<RoomDTO> listDTO = new ArrayList<>();
	
	@Mock
	private RoomService roomService;
	
	@InjectMocks 
	private RoomController roomController = new RoomController();
	
	@Before
	public void initRoom() throws NotFoundException {
		roomDTO.setId(ID);
		roomDTOException.setId(IDException);
		
		listDTO.add(roomDTO);
		Mockito.when(roomService.findAll(PAGE, SIZE)).thenReturn(listDTO);
		Mockito.when(roomService.findDTOById(ID)).thenReturn(roomDTO);
		Mockito.when(roomService.findDTOById(-1)).thenThrow(new NotFoundException());
		Mockito.doNothing().when(roomService).update(roomDTO);
		Mockito.doNothing().when(roomService).delete(ID);
		Mockito.when(roomService.create(roomDTO)).thenReturn(roomDTO);
	}
	
	@Test
	public void testFindAll(){
		List<RoomDTO> result = roomController.findAll(PAGE, SIZE);
		
		Assert.assertEquals(result.get(0).getId(),listDTO.get(0).getId());
	}
	
	@Test(expected = NotFoundException.class)
	public void TestfindOneByIdException() throws NotFoundException {
		roomController.findOneById(-1);
	}
	
	@Test
	public void TestfindOneById() throws NotFoundException{
		RoomDTO result = roomController.findOneById(ID);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(result.getId(), ID);
	}
	
	@Test
	public void TestCreate() throws NotFoundException {
		RoomDTO result = roomController.create(roomDTO);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(result.getId(), ID);
	}
	
	@Test
	public void TestUpdate() throws NotFoundException{
		roomController.update(roomDTO, ID);
	}
	
	@Test
	public void TestDelete() {
		roomController.delete(roomDTO, ID);
	}	
}
