package com.jiniguez.demo.Service.Implementation;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jiniguez.demo.Config.CustomPageRequest;
import com.jiniguez.demo.DAO.DoctorDAO;
import com.jiniguez.demo.DTO.ConsultationDTO;
import com.jiniguez.demo.DTO.DoctorDTO;
import com.jiniguez.demo.DTO.PatientDTO;
import com.jiniguez.demo.DTO.StatisticsDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Model.Consultation;
import com.jiniguez.demo.Model.Doctor;
import com.jiniguez.demo.Service.ConsultationService;
import com.jiniguez.demo.Service.DoctorService;
import com.jiniguez.demo.Service.PatientService;

@Service
public class DoctorServiceImpl implements DoctorService {

	/**
	 * QUE NO SE PUEDE DE ENCONTRA, PARA QUE CUANDO YO QUIERA ENCONTRA, NO ENCUENTRE, Y PARA ENCONTRA LO NO ENCONTRADO, USO ESTA VARIABLE
	 * DE ESTA MANERA ENCUENTRO LO QUE NO PODIA ENCONTRAR
	 */
	private static final int NOT_FINDABLE_ID = -1;

	@Autowired
	DoctorDAO doctorDAO;
	
	@Autowired
	PatientService patientService;
	
	@Autowired
	ConsultationService consultationService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DozerBeanMapper dozer;

	@Override
	public DoctorDTO doctorToDTO(Doctor doctor) {
		return dozer.map(doctor, DoctorDTO.class);
	}
	
	@Override
	public Doctor DTOToDoctor(DoctorDTO doctorDTO) {	
		Doctor doctor = doctorDAO.findOne(Optional.ofNullable(doctorDTO.getInternalId()).orElse(NOT_FINDABLE_ID));
		
		//Si no existe el doctor, creo uno.
		if(doctor == null)
			doctor = new Doctor();
		
		doctor.setId(doctorDTO.getId());
		doctor.setName(doctorDTO.getName());
		doctor.setEmail(doctorDTO.getEmail());
		doctor.setPrice(doctorDTO.getPrice());
		
		return doctor;
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
			consultations.add(consultationService.consultationToDTO(c));
		});
		return consultations;
	}

	@Override
	public List<StatisticsDTO> getStats(String initDate, String endDate) throws NotFoundException, ParseException {
		List<StatisticsDTO> result = new ArrayList<>();
		
		SimpleDateFormat s = new SimpleDateFormat("DD-MM-YY");
		
		DoctorDTO[] doctors;
		int j = 0;

		do {
			ResponseEntity<DoctorDTO[]> docs = restTemplate
					.getForEntity("http://doctor.dbgjerez.es:8080/api/doctor?page="+j, DoctorDTO[].class);

			doctors = docs.getBody();
		
			for (int i = 0; i < doctors.length; i++) {
				doctors[i] = restTemplate.getForObject("http://doctor.dbgjerez.es:8080/api/doctor/"+doctors[i].getId(), DoctorDTO.class);
				if(doctorDAO.findOneByExternalID(doctors[i].getId()) == null)
					create(doctors[i]);
			}
			j++;
		}while (doctors.length > 0);
		
		
		List<Consultation> consultations = consultationService.findAll();
		
		for (Consultation consultation : consultations) {
			if(isBetween(consultation.getDay(), s.parse(initDate), s.parse(endDate))) {
				Double price = consultation.getDoctor().getPrice() * consultation.getAppointments().size();
				Integer doctorID = consultation.getDoctor().getInternalId();
				Integer totalConsultations = 1;
				StatisticsDTO stat = new StatisticsDTO(totalConsultations, doctorID, price);
				int index = -1;
				
				index = result.indexOf(stat);
				if(index!= -1) {
					stat = result.get(index);
					stat.setTotalPrice(stat.getTotalPrice() + price);
					stat.setConsultationsAmount(stat.getConsultationsAmount()+1);
					result.set(index, stat);
				}else
					result.add(stat);
			}
		}
		
		return result;
	}

	private boolean isBetween(Date dateToCheck, Date initDate, Date endDate) {
		return !dateToCheck.before(initDate) && !dateToCheck.after(endDate);
	}

	@Override
	public Doctor findByExternalId(String externalID) {
		return doctorDAO.findOneByExternalID(externalID);
	}

}
