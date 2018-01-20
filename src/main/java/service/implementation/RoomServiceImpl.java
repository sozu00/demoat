package service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import DTO.RoomDTO;
import dao.RoomDAO;
import model.Room;
import service.RoomService;


public class RoomServiceImpl implements RoomService {

	@Autowired
	RoomDAO roomDAO;
	
	@Autowired
	private DozerBeanMapper dozer;

	private RoomDTO roomToDTO(Room room) {
		return dozer.map(room, RoomDTO.class);
	}
	
	private Room DTOToRoom(RoomDTO room) {
		return dozer.map(room, Room.class);
	}
	
	@Override
	public RoomDTO findById(Integer id) {
		final Room a = roomDAO.findOne(id);
		return roomToDTO(a);
	}

	@Override
	public List<RoomDTO> findAll(Integer page, Integer size) {
		final Iterable<Room> findAll = roomDAO.findAll();
		final List<RoomDTO> res = new ArrayList<>();
		findAll.forEach(b ->{
				final RoomDTO aDTO = roomToDTO(b);
				res.add(aDTO);
			});
		return res;
	}
	
	@Override
	public RoomDTO create(RoomDTO room) {
		final Room a = DTOToRoom(room);
		return roomToDTO(roomDAO.save(a));
	}

	@Override
	public void update(RoomDTO room) {
		final Room a = DTOToRoom(room);
		roomDAO.save(a);		
	}

	@Override
	public void delete(Integer id) {
		roomDAO.delete(id);
	}
}
