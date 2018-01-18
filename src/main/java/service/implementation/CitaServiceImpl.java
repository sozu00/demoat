package service.implementation;

import org.springframework.beans.factory.annotation.Autowired;

import dao.CitaDAO;
import model.Cita;
import service.CitaService;


public class CitaServiceImpl implements CitaService {

	@Autowired
	CitaDAO dao;
	
	@Override
	public Cita findById(Integer id) {
		return dao.findOne(id);
	}
}
