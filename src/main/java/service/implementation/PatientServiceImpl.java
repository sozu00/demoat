package service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import DTO.PatientDTO;
import dao.PatientDAO;
import model.Patient;
import service.PatientService;


public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientDAO patientDAO;
	
	@Autowired
	private DozerBeanMapper dozer;

	private PatientDTO patientToDTO(Patient patient) {
		return dozer.map(patient, PatientDTO.class);
	}
	
	private Patient DTOTopatient(PatientDTO patient) {
		return dozer.map(patient, Patient.class);
	}
	
	@Override
	public PatientDTO findById(Integer id) {
		final Patient a = patientDAO.findOne(id);
		return patientToDTO(a);
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
}
