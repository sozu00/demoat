package com.jiniguez.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jiniguez.demo.DTO.RoomDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Service.RoomService;

import lombok.extern.java.Log;

@RestController
@RequestMapping(value = "api/room")
@Log
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@RequestMapping(method = { RequestMethod.GET })
	public List<RoomDTO> findAll(@RequestParam(required=false) Integer page,
			@RequestParam(required = false) Integer size){
		log.info("Buscando todas las habitaciones");
		return roomService.findAll(page, size);
	}
	
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public RoomDTO findOneById(@PathVariable Integer id) throws NotFoundException {
		log.info("Buscando la habitacion con id = "+id);
		return roomService.findDTOById(id);
	}
	
	@RequestMapping(method = {RequestMethod.POST})
	public RoomDTO create(@RequestBody RoomDTO room) throws NotFoundException {
		return roomService.create(room);
	}
	
	@RequestMapping(value = "/{id}",method = {RequestMethod.PUT})
	public void update(@RequestBody RoomDTO room, 
			@PathVariable("id") Integer id) throws NotFoundException {
		roomService.update(room);
	}
	
	@RequestMapping(value = "/{id}",method = {RequestMethod.DELETE})
	public void delete(@RequestBody RoomDTO room, 
			@PathVariable("id") Integer id) {
		roomService.delete(id);
	}
}

