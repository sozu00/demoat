package com.jiniguez.demo.Service.Implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiniguez.demo.DAO.ClinicDAO;
import com.jiniguez.demo.DTO.ClinicDTO;
import com.jiniguez.demo.DTO.DoctorDTO;
import com.jiniguez.demo.DTO.PatientDTO;
import com.jiniguez.demo.DTO.RoomDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Model.Clinic;
import com.jiniguez.demo.Service.ClinicService;
import com.jiniguez.demo.Service.DoctorService;
import com.jiniguez.demo.Service.PatientService;
import com.jiniguez.demo.Service.RoomService;

@Service
public class ClinicServiceImpl implements ClinicService {

	@Autowired
	ClinicDAO clinicDAO;

	@Autowired
	private PatientService patientService;
	
	@Autowired
	private DoctorService doctorService;

	@Autowired
	private RoomService roomService;
	
	@Autowired
	private DozerBeanMapper dozer;

	@Override
	public ClinicDTO clinicToDTO(Clinic clinic) {
		return dozer.map(clinic, ClinicDTO.class);
	}
	
	@Override
	public Clinic DTOToClinic(ClinicDTO clinic) {
		Clinic c = Optional.ofNullable(clinicDAO.findOne(clinic.getId())).orElse(new Clinic());
		c.setName(clinic.getName());
		return c;
	}
	
	@Override
	public ClinicDTO findDTOById(Integer id) throws NotFoundException {
		return clinicToDTO(findById(id));
	}
	@Override
	public Clinic findById(Integer id) throws NotFoundException {
		return Optional.ofNullable(clinicDAO.findOne(id))
        		.orElseThrow(() -> new NotFoundException());
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
	public List<PatientDTO> findPatients(Integer id) throws NotFoundException {
		Map<Integer, PatientDTO> patients = new HashMap<>();
		
		findById(id).getRooms()
		.forEach(r -> {
			r.getConsultations()
			.forEach(c ->{
				c.getAppointments()
				.forEach(a ->{
					if(!patients.containsKey(a.getId()))
						patients.put(a.getId(),patientService.patientToDTO(a.getPatient()));
				});
			});
		});
		
		return new ArrayList<>(patients.values());
	}
	
	@Override
	public List<DoctorDTO> findDoctors(Integer id) throws NotFoundException {
		Map<Integer, DoctorDTO> doctors = new HashMap<>();
		
		findById(id).getRooms()
		.forEach(r -> {
			r.getConsultations()
			.forEach(c ->{
				if(!doctors.containsKey(c.getDoctor().getId()))
					doctors.put(c.getDoctor().getId(),doctorService.doctorToDTO(c.getDoctor()));
			});
		});
		
		return new ArrayList<>(doctors.values());
	}
	
	@Override
	public List<RoomDTO> findRooms(Integer id) throws NotFoundException {
		List<RoomDTO> rooms = new ArrayList<>();
		findById(id).getRooms().forEach(r -> {rooms.add(roomService.roomToDTO(r));});
		return rooms;
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
