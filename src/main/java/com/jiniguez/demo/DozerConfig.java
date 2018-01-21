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
		DozerBeanMapper dozer = new DozerBeanMapper();
		dozer.addMapping(new BeanMappingBuilder() {
			@Override
			protected void configure() {
				mapping(AppointmentDTO.class, Appointment.class)
				.fields("patient_id", "patient.id")
				.fields("consultation_id", "consultation.id");
			}
		});
		return dozer;
	}
}
