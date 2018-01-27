package com.jiniguez.demo.Service;

import java.util.List;
import com.jiniguez.demo.DTO.RoomDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Model.Room;

public interface RoomService {

	Room findById(Integer id) throws NotFoundException;

	List<RoomDTO> findAll(Integer page, Integer size);

	RoomDTO create(RoomDTO room) throws NotFoundException;

	void update(RoomDTO room) throws NotFoundException;

	void delete(Integer id);

	RoomDTO findDTOById(Integer id) throws NotFoundException;

	RoomDTO roomToDTO(Room room);

	Room DTOToRoom(RoomDTO room) throws NotFoundException;
	
}
