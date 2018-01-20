package com.jiniguez.demo.DAO;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jiniguez.demo.Model.Doctor;

public interface DoctorDAO extends PagingAndSortingRepository<Doctor, Integer>{

}
