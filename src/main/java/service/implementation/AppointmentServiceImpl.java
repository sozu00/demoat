package service.implementation;

import org.springframework.beans.factory.annotation.Autowired;

import dao.AppointmentDAO;
import model.Appointment;
import service.AppointmentService;


public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	AppointmentDAO dao;
	
	@Override
	public Appointment findById(Integer id) {
		return dao.findOne(id);
	}
}
