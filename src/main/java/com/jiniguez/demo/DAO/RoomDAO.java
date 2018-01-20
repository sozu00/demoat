package com.jiniguez.demo.DAO;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jiniguez.demo.Model.Room;

public interface RoomDAO extends PagingAndSortingRepository<Room, Integer>{

}
