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
import com.jiniguez.demo.Service.RoomService;

@RestController
@RequestMapping(value = "api/room")
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	public List<RoomDTO> findAll(@RequestParam(required=false) Integer page,
			@RequestParam(required = false) Integer size){
		return roomService.findAll(page, size);
	}
	
	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public RoomDTO findOneById(@PathVariable Integer id) {
		return roomService.findById(id);
	}
	
	@RequestMapping(method = {RequestMethod.POST})
	public RoomDTO create(@RequestBody RoomDTO room) {
		return roomService.create(room);
	}
	
	@RequestMapping(value = "/{id}",method = {RequestMethod.PUT})
	public void update(@RequestBody RoomDTO room, 
			@PathVariable("id") Integer id) {
		roomService.update(room);
	}
	
	@RequestMapping(value = "/{id}",method = {RequestMethod.PUT})
	public void delete(@RequestBody RoomDTO room, 
			@PathVariable("id") Integer id) {
		roomService.delete(id);
	}
}

