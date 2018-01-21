package com.jiniguez.demo.Service.Implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiniguez.demo.DAO.ClinicDAO;
import com.jiniguez.demo.DTO.ClinicDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Model.Clinic;
import com.jiniguez.demo.Service.ClinicService;

@Service
public class ClinicServiceImp implements ClinicService {

	@Autowired
	ClinicDAO clinicDAO;
	
	@Autowired
	private DozerBeanMapper dozer;

	private ClinicDTO clinicToDTO(Clinic clinic) {
		return dozer.map(clinic, ClinicDTO.class);
	}
	
	private Clinic DTOToClinic(ClinicDTO clinic) {
		return dozer.map(clinic, Clinic.class);
	}
	
	@Override
	public ClinicDTO findById(Integer id) throws NotFoundException {
		Clinic a = Optional.ofNullable(clinicDAO.findOne(id))
        		.orElseThrow(() -> new NotFoundException());
		return clinicToDTO(a);
	}

	@Override
	public List<ClinicDTO> findAll(Integer page, Integer size) {
		final Iterable<Clinic> findAll = clinicDAO.findAll();
		final List<ClinicDTO> res = new ArrayList<>();
		findAll.forEach(b ->{
				final ClinicDTO aDTO = clinicToDTO(b);
				res.add(aDTO);
			});
		return res;
	}
	
	@Override
	public ClinicDTO create(ClinicDTO clinic) {
		final Clinic a = DTOToClinic(clinic);
		return clinicToDTO(clinicDAO.save(a));
	}

	@Override
	public void update(ClinicDTO clinic) {
		final Clinic a = DTOToClinic(clinic);
		clinicDAO.save(a);		
	}

	@Override
	public void delete(Integer id) {
		clinicDAO.delete(id);
	}
}
