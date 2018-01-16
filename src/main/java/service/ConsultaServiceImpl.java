package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ConsultaDAO;
import model.Consulta;
import model.Medico;

@Service
public class ConsultaServiceImpl implements ConsultaService {

	@Autowired
	ConsultaDAO dao;
	
	@Autowired 
	MedicoService medicoService;
	
	@Override
	public Consulta findById(Integer id) {
		return dao.findOne(id);
	}
	
	@Override
	public Medico findDoctorByConsultaId(Integer id) {
		return dao.findOne(id).getMedico();
	}
}
