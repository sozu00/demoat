package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.SalaDAO;
import model.Sala;

@Service
public class SalaServiceImpl implements SalaService {

	@Autowired
	SalaDAO dao;
	
	@Override
	public Sala findById(Integer id) {
		return dao.findOne(id);
	}
}
