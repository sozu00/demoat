package dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import model.Pacient;

public interface PacientDAO extends PagingAndSortingRepository<Pacient, Integer>{

}
