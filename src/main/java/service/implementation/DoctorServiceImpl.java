package service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import DTO.DoctorDTO;
import dao.DoctorDAO;
import model.Doctor;
import service.DoctorService;


public class DoctorServiceImpl implements DoctorService {

	@Autowired
	DoctorDAO doctorDAO;
	
	@Autowired
	private DozerBeanMapper dozer;

	private DoctorDTO doctorToDTO(Doctor doctor) {
		return dozer.map(doctor, DoctorDTO.class);
	}
	
	private Doctor DTOToDoctor(DoctorDTO doctor) {
		return dozer.map(doctor, Doctor.class);
	}
	
	@Override
	public DoctorDTO findById(Integer id) {
		final Doctor a = doctorDAO.findOne(id);
		return doctorToDTO(a);
	}

	@Override
	public List<DoctorDTO> findAll(Integer page, Integer size) {
		final Iterable<Doctor> findAll = doctorDAO.findAll();
		final List<DoctorDTO> res = new ArrayList<>();
		findAll.forEach(b ->{
				final DoctorDTO aDTO = doctorToDTO(b);
				res.add(aDTO);
			});
		return res;
	}
	
	@Override
	public DoctorDTO create(DoctorDTO doctor) {
		final Doctor a = DTOToDoctor(doctor);
		return doctorToDTO(doctorDAO.save(a));
	}

	@Override
	public void update(DoctorDTO doctor) {
		final Doctor a = DTOToDoctor(doctor);
		doctorDAO.save(a);		
	}

	@Override
	public void delete(Integer id) {
		doctorDAO.delete(id);
	}

}
