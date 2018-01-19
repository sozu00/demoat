package dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import model.Room;

public interface RoomDAO extends PagingAndSortingRepository<Room, Integer>{

}
