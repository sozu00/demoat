package service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ClinicDAO;
import model.Clinic;
import service.ClinicService;


public class ClinicServiceImpl implements ClinicService {

	@Autowired
	ClinicDAO dao;
	
	@Override
	public Clinic findById(Integer id) {
		return dao.findOne(id);
	}
}
