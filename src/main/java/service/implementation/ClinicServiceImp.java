package service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import DTO.ClinicDTO;
import dao.ClinicDAO;
import model.Clinic;
import service.ClinicService;


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
	public ClinicDTO findById(Integer id) {
		final Clinic a = clinicDAO.findOne(id);
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
