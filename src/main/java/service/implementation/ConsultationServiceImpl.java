package service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ConsultationDAO;
import model.Consultation;
import model.Doctor;
import service.ConsultationService;
import service.DoctorService;

public class ConsultationServiceImpl implements ConsultationService {

	@Autowired
	ConsultationDAO dao;
	
	@Autowired 
	DoctorService medicoService;
	
	@Override
	public Consultation findById(Integer id) {
		return dao.findOne(id);
	}
	
	@Override
	public Doctor findDoctorByConsultaId(Integer id) {
		return null;
	}
}
