package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CitaDAO;
import model.Cita;

@Service
public class CitaServiceImpl implements CitaService {

	@Autowired
	CitaDAO dao;
	
	@Override
	public Cita findById(Integer id) {
		return dao.findOne(id);
	}
}
