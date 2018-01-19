package dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import model.Doctor;

public interface DoctorDAO extends PagingAndSortingRepository<Doctor, Integer>{

}
