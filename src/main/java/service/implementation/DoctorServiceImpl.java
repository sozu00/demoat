package service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DoctorDAO;
import model.Doctor;
import service.DoctorService;


public class DoctorServiceImpl implements DoctorService {

	@Autowired
	DoctorDAO dao;
	
	@Override
	public Doctor findById(Integer id) {
		return dao.findOne(id);
	}
}
