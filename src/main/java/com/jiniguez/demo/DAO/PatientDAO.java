package com.jiniguez.demo.DAO;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jiniguez.demo.Model.Patient;

public interface PatientDAO extends PagingAndSortingRepository<Patient, Integer>{

}
