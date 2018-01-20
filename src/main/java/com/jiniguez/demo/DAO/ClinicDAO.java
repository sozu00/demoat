package com.jiniguez.demo.DAO;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jiniguez.demo.Model.Clinic;

public interface ClinicDAO extends PagingAndSortingRepository<Clinic, Integer>{

}
