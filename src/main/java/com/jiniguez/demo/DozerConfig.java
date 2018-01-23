package com.jiniguez.demo;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jiniguez.demo.DTO.AppointmentDTO;
import com.jiniguez.demo.Model.Appointment;

@Configuration
public class DozerConfig {
	@Bean
	public DozerBeanMapper dozer() {
		return new DozerBeanMapper();
	}
}
