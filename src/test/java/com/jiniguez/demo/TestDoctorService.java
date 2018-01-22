package com.jiniguez.demo;

import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.jiniguez.demo.DAO.DoctorDAO;
import com.jiniguez.demo.DTO.DoctorDTO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Model.Doctor;
import com.jiniguez.demo.Service.DoctorService;
import com.jiniguez.demo.Service.Implementation.DoctorServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestDoctorService {

	@InjectMocks 
	private DoctorService service = new DoctorServiceImpl();

	@Mock
	DoctorDAO doctorDAO;
	
	@Mock
	private DozerBeanMapper dozer;

	
	@Test
	public void testFindByIdBase() throws NotFoundException {
		final Doctor doctor = mockDoctor();
		
		Mockito.when(doctorDAO.findOne(8)).thenReturn(doctor);
		
		final Doctor res = service.findById(8);
		
		Assert.assertNotNull(res);
		Assert.assertEquals("Jesus", res.getName());
	}
	
	

	@Test(expected = NotFoundException.class)
	public void testFindByIdNotFound() throws NotFoundException{
		service.findById(999);
	}


	private Doctor mockDoctor() {
		final Doctor doctor = new Doctor();
		doctor.setId(8);
		doctor.setName("Jesus");
		return doctor;
	}
}
