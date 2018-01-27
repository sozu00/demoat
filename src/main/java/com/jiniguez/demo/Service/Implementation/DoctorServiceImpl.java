package com.jiniguez.demo.Service.Implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jiniguez.demo.Config.CustomPageRequest;
import com.jiniguez.demo.DAO.DoctorDAO;
import com.jiniguez.demo.DTO.ConsultationDTO;
import com.jiniguez.demo.DTO.DoctorDTO;
import com.jiniguez.demo.DTO.PatientDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Model.Doctor;
import com.jiniguez.demo.Service.ConsultationService;
import com.jiniguez.demo.Service.DoctorService;
import com.jiniguez.demo.Service.PatientService;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	DoctorDAO doctorDAO;
	
	@Autowired
	PatientService patientService;
	
	@Autowired
	ConsultationService consulationService;
	
	@Autowired
	private DozerBeanMapper dozer;

	@Override
	public DoctorDTO doctorToDTO(Doctor doctor) {
		return dozer.map(doctor, DoctorDTO.class);
	}
	
	@Override
	public Doctor DTOToDoctor(DoctorDTO doctor) {
		Doctor d = Optional.ofNullable(doctorDAO.findOne(doctor.getId())).orElse(new Doctor());
		d.setName(doctor.getName());
		d.setEmail(doctor.getEmail());

		return d;
	}
	
	@Override
	public DoctorDTO findDTOById(Integer id) throws NotFoundException {
        return doctorToDTO(findById(id));
	}
	
	@Override
	public Doctor findById(Integer id) throws NotFoundException {
        return Optional.ofNullable(doctorDAO.findOne(id))
        		.orElseThrow(()-> new NotFoundException());
	}

	@Override
	public List<DoctorDTO> findAll(Integer page, Integer size) {
		final Iterable<Doctor> findAll = doctorDAO.findAll(CustomPageRequest.newPageRequest(page, size));
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
		List<Doctor> list = doctorDAO.findTopNDoctorsWithMorePatients();
		List<DoctorDTO> result = new ArrayList<>();
		list.stream().limit(num).collect(Collectors.toList()).forEach((d)->{
			result.add(doctorToDTO(d));
		});
		return result;
	}

	@Override
	public List<PatientDTO> findPatients(Integer id) throws NotFoundException {
		Map<Integer, PatientDTO> patients = new HashMap<>();
		
		findById(id).getConsultations()
		.forEach(c ->{
			c.getAppointments()
			.forEach(a ->{
				if(!patients.containsKey(a.getId()))
					patients.put(a.getId(),patientService.patientToDTO(a.getPatient()));
			});
		});
		return new ArrayList<>(patients.values());
	}

	@Override
	public List<ConsultationDTO> findConsultations(Integer id) throws NotFoundException {
		List <ConsultationDTO> consultations = new ArrayList<>();
		
		findById(id).getConsultations().forEach(c -> {
			consultations.add(consulationService.consultationToDTO(c));
		});
		return consultations;
	}

}
