package com.jiniguez.demo.Service.Implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiniguez.demo.Config.CustomPageRequest;
import com.jiniguez.demo.DAO.RoomDAO;
import com.jiniguez.demo.DTO.RoomDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Model.Room;
import com.jiniguez.demo.Service.ClinicService;
import com.jiniguez.demo.Service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	RoomDAO roomDAO;
	
	@Autowired
	ClinicService clinicService;
	
	@Autowired
	private DozerBeanMapper dozer;

	@Override
	public RoomDTO roomToDTO(Room room) {
		return dozer.map(room, RoomDTO.class);
	}
	
	@Override
	public Room DTOToRoom(RoomDTO room) throws NotFoundException {
		Room r = Optional.ofNullable(roomDAO.findOne(room.getId())).orElse(new Room());
		r.setClinic(clinicService.findById(room.getClinic_id()));
		r.setRoomNumber(room.getRoomNumber());
		return r;
	}
	
	@Override
	public Room findById(Integer id) throws NotFoundException {
		return Optional.ofNullable(roomDAO.findOne(id))
        		.orElseThrow(() -> new NotFoundException());
	}
	
	@Override
	public RoomDTO findDTOById(Integer id) throws NotFoundException {
		return roomToDTO(findById(id));
	}

	@Override
	public List<RoomDTO> findAll(Integer page, Integer size) {
		final Iterable<Room> findAll = roomDAO.findAll(CustomPageRequest.newPageRequest(page, size));
		final List<RoomDTO> res = new ArrayList<>();
		findAll.forEach(b ->{
				final RoomDTO aDTO = roomToDTO(b);
				res.add(aDTO);
			});
		return res;
	}
	
	@Override
	public RoomDTO create(RoomDTO room) throws NotFoundException {
		final Room a = DTOToRoom(room);
		return roomToDTO(roomDAO.save(a));
	}

	@Override
	public void update(RoomDTO room) throws NotFoundException {
		final Room a = DTOToRoom(room);
		roomDAO.save(a);		
	}

	@Override
	public void delete(Integer id) {
		roomDAO.delete(id);
	}
}
