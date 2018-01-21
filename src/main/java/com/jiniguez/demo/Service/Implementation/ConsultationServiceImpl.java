package com.jiniguez.demo.Service.Implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiniguez.demo.DAO.ConsultationDAO;
import com.jiniguez.demo.DTO.ConsultationDTO;
import com.jiniguez.demo.DTO.DoctorDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Model.Consultation;
import com.jiniguez.demo.Service.ConsultationService;
import com.jiniguez.demo.Service.DoctorService;

@Service
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
	public ConsultationDTO findById(Integer id) throws NotFoundException {
		Consultation a = Optional.ofNullable(consultationDAO.findOne(id))
        		.orElseThrow(() -> new NotFoundException());
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
	public DoctorDTO findDoctorByConsultaId(Integer id) throws NotFoundException {
		ConsultationDTO aDTO = consultationToDTO(consultationDAO.findOne(id));
		return doctorService.findById(Integer.valueOf(aDTO.getDoctor()));
	}
}
