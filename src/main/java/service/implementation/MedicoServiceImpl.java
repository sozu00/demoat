package service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MedicoDAO;
import model.Medico;
import service.MedicoService;


public class MedicoServiceImpl implements MedicoService {

	@Autowired
	MedicoDAO dao;
	
	@Override
	public Medico findById(Integer id) {
		return dao.findOne(id);
	}
}
