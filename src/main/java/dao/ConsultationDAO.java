package dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import model.Consultation;

public interface ConsultationDAO extends PagingAndSortingRepository<Consultation, Integer>{

}
