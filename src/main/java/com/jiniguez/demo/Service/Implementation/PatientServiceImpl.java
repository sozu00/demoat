package com.jiniguez.demo.Service.Implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiniguez.demo.DAO.PatientDAO;
import com.jiniguez.demo.DTO.AppointmentDTO;
import com.jiniguez.demo.DTO.PatientDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Model.Appointment;
import com.jiniguez.demo.Model.Patient;
import com.jiniguez.demo.Service.AppointmentService;
import com.jiniguez.demo.Service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientDAO patientDAO;
	
	@Autowired
	AppointmentService appointmentService;
	
	@Autowired
	private DozerBeanMapper dozer;

	private PatientDTO patientToDTO(Patient patient) {
		return dozer.map(patient, PatientDTO.class);
	}
	
	private Patient DTOTopatient(PatientDTO patient) {
		return dozer.map(patient, Patient.class);
	}
	
	@Override
	public PatientDTO findDTOById(Integer id) throws NotFoundException {
		return patientToDTO(findById(id));
	}
	
	@Override
	public Patient findById(Integer id) throws NotFoundException {
		return Optional.ofNullable(patientDAO.findOne(id))
        		.orElseThrow(() -> new NotFoundException());
	}

	@Override
	public List<PatientDTO> findAll(Integer page, Integer size) {
		final Iterable<Patient> findAll = patientDAO.findAll();
		final List<PatientDTO> res = new ArrayList<>();
		findAll.forEach(b ->{
				final PatientDTO aDTO = patientToDTO(b);
				res.add(aDTO);
			});
		return res;
	}
	
	@Override
	public PatientDTO create(PatientDTO patient) {
		final Patient a = DTOTopatient(patient);
		return patientToDTO(patientDAO.save(a));
	}

	@Override
	public void update(PatientDTO patient) {
		final Patient a = DTOTopatient(patient);
		patientDAO.save(a);		
	}

	@Override
	public void delete(Integer id) {
		patientDAO.delete(id);
	}

	@Override
	public List<AppointmentDTO> findAppointments(Integer id) throws NotFoundException{
		Patient p = Optional.ofNullable(patientDAO.findOne(id))
        		.orElseThrow(() -> new NotFoundException());
		List<AppointmentDTO> result = new ArrayList<AppointmentDTO>();
		for (Appointment a : p.getAppointments()) {
			result.add(appointmentService.findDTOById(a.getId()));
		}
		return result;
	}
}
