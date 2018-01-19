package service;

import java.util.List;

import org.springframework.stereotype.Service;

import DTO.RoomDTO;

@Service
public interface RoomService {

	RoomDTO findById(Integer id);

	List<RoomDTO> findAll(Integer page, Integer size);

	RoomDTO create(RoomDTO room);

	void update(RoomDTO room);

	void delete(Integer id);
	
}
