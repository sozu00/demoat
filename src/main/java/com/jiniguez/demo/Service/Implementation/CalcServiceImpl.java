package com.jiniguez.demo.Service.Implementation;

import com.jiniguez.demo.Service.CalcService;

public class CalcServiceImpl implements CalcService {

	@Override
	public Integer suma(Integer a, Integer b) {
		return a+b;
	}

	@Override
	public Integer mult(Integer a, Integer b) {
		Integer c = 0;

		for(int i = 0 ; i < a; i++) {
			c = suma(c, b);
		}
		return c;
	}

}
