package service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.PacientDAO;
import model.Pacient;
import service.PacientService;


public class PacientServiceImpl implements PacientService {

	@Autowired
	PacientDAO dao;
	
	@Override
	public Pacient findById(Integer id) {
		return dao.findOne(id);
	}
}
