package com.jiniguez.demo.Service;

import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.jiniguez.demo.DAO.DoctorDAO;
import com.jiniguez.demo.Exceptions.NotFoundException;
import com.jiniguez.demo.Model.Doctor;
import com.jiniguez.demo.Service.Implementation.DoctorServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestDoctorService {

	private static final Integer ID = 8;

	private static final String EMAIL = "correito@correito.com";

	private static final String NAME = "nombresito";

	private static Doctor DOCTOR = new Doctor();
	
	@InjectMocks 
	private DoctorService service = new DoctorServiceImpl();

	@Mock
	DoctorDAO doctorDAO;
	
	@Mock
	private DozerBeanMapper dozer;
	
	@Before
	public void init() {
		DOCTOR.setInternalId(ID);
		DOCTOR.setEmail(EMAIL);
		DOCTOR.setName(NAME);
		Mockito.when(doctorDAO.findOne(ID)).thenReturn(DOCTOR);
	}
	
	
	@Test
	public void testFindByIdBase() throws NotFoundException {
		final Doctor res = service.findById(ID);

		Assert.assertNotNull(res);
		Assert.assertEquals(NAME, res.getName());
	}
	
	

	@Test(expected = NotFoundException.class)
	public void testFindByIdNotFound() throws NotFoundException{
		service.findById(999);
	}


}
