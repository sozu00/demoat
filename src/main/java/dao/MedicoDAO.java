package dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import model.Medico;

public interface MedicoDAO extends PagingAndSortingRepository<Medico, Integer>{

}
