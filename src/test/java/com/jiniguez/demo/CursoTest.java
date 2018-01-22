package com.jiniguez.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.jiniguez.demo.Service.CalcService;
import com.jiniguez.demo.Service.Implementation.CalcServiceImpl;

import org.junit.Assert;

@RunWith(MockitoJUnitRunner.class)
public class CursoTest {

	private CalcService service = new CalcServiceImpl();
	
	@Test
	public void testSuma() {
		final Integer suma = service.suma(1,2);
		Assert.assertEquals(suma, Integer.valueOf(3));
	}
	
	@Test
	public void testmulti() {
//		Mockito.when(service.suma(8, 8)).thenReturn(16);
		
		final Integer mult = service.mult(2, 8);
		Assert.assertEquals(mult, Integer.valueOf(16));
	}
}
