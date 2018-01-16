package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.PacienteDAO;
import model.Paciente;

@Service
public class PacienteServiceImpl implements PacienteService {

	@Autowired
	PacienteDAO dao;
	
	@Override
	public Paciente findById(Integer id) {
		return dao.findOne(id);
	}
}
