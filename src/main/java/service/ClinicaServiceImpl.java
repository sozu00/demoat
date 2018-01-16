package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ClinicaDAO;
import model.Clinica;

@Service
public class ClinicaServiceImpl implements ClinicaService {

	@Autowired
	ClinicaDAO dao;
	
	@Override
	public Clinica findById(Integer id) {
		return dao.findOne(id);
	}
}
