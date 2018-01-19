package service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.RoomDAO;
import model.Room;
import service.RoomService;


public class RoomServiceImpl implements RoomService {

	@Autowired
	RoomDAO dao;
	
	@Override
	public Room findById(Integer id) {
		return dao.findOne(id);
	}
}
