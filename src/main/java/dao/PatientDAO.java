package dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import model.Patient;

public interface PatientDAO extends PagingAndSortingRepository<Patient, Integer>{

}
