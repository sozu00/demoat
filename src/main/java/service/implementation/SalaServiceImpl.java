package service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.SalaDAO;
import model.Sala;
import service.SalaService;

@Service
public class SalaServiceImpl implements SalaService {

	@Autowired
	SalaDAO dao;
	
	@Override
	public Sala findById(Integer id) {
		return dao.findOne(id);
	}
}
