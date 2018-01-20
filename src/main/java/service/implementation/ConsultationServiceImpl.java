package service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import DTO.ConsultationDTO;
import DTO.DoctorDTO;
import dao.ConsultationDAO;
import model.Consultation;
import service.ConsultationService;
import service.DoctorService;


public class ConsultationServiceImpl implements ConsultationService {

	@Autowired
	ConsultationDAO consultationDAO;
	
	@Autowired
	DoctorService doctorService;
	
	@Autowired
	private DozerBeanMapper dozer;

	private ConsultationDTO consultationToDTO(Consultation consultation) {
		return dozer.map(consultation, ConsultationDTO.class);
	}
	
	private Consultation DTOToConsultation(ConsultationDTO consultation) {
		return dozer.map(consultation, Consultation.class);
	}
	
	@Override
	public ConsultationDTO findById(Integer id) {
		final Consultation a = consultationDAO.findOne(id);
		return consultationToDTO(a);
	}

	@Override
	public List<ConsultationDTO> findAll(Integer page, Integer size) {
		final Iterable<Consultation> findAll = consultationDAO.findAll();
		final List<ConsultationDTO> res = new ArrayList<>();
		findAll.forEach(b ->{
				final ConsultationDTO aDTO = consultationToDTO(b);
				res.add(aDTO);
			});
		return res;
	}
	
	@Override
	public ConsultationDTO create(ConsultationDTO consultation) {
		final Consultation a = DTOToConsultation(consultation);
		return consultationToDTO(consultationDAO.save(a));
	}

	@Override
	public void update(ConsultationDTO consultation) {
		final Consultation a = DTOToConsultation(consultation);
		consultationDAO.save(a);		
	}

	@Override
	public void delete(Integer id) {
		consultationDAO.delete(id);
	}

	@Override
	public DoctorDTO findDoctorByConsultaId(Integer id) {
		ConsultationDTO aDTO = consultationToDTO(consultationDAO.findOne(id));
		return doctorService.findById(aDTO.getDoctor());
	}
}
