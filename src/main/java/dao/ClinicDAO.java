package dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import model.Clinic;

public interface ClinicDAO extends PagingAndSortingRepository<Clinic, Integer>{

}
