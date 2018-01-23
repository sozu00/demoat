package com.jiniguez.demo.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jiniguez.demo.Model.Consultation;

public interface ConsultationDAO extends CrudRepository<Consultation, Integer>{

}
