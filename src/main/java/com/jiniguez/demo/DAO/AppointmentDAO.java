package com.jiniguez.demo.DAO;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jiniguez.demo.Model.Appointment;

public interface AppointmentDAO extends PagingAndSortingRepository<Appointment, Integer>{


}
