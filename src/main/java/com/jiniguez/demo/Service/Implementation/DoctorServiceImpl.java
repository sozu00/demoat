package com.jiniguez.demo.Service.Implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiniguez.demo.DAO.DoctorDAO;
import com.jiniguez.demo.DTO.DoctorDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Model.Doctor;
import com.jiniguez.demo.Service.DoctorService;

@Service
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
	public DoctorDTO findById(Integer id) throws NotFoundException {
        Doctor optDoctor = Optional.ofNullable(doctorDAO.findOne(id))
        		.orElseThrow(() -> new NotFoundException());
        return doctorToDTO(optDoctor);
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

	@Override
	public List<DoctorDTO> findByName(String name) {
		final Iterable<Doctor> findAll = doctorDAO.findAll();
		final List<DoctorDTO> res = new ArrayList<>();
		findAll.forEach(b ->{
			final DoctorDTO aDTO = doctorToDTO(b);
			if(b.getName().equals(name))
				res.add(aDTO);
			});
		return res;
	}

	@Override
	public List<DoctorDTO> findTopNDoctorsWithMorePatients(Integer num) {
		return doctorDAO.findTopNDoctorsWithMorePatients(num);
	}

}
