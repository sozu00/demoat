package com.jiniguez.demo.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Appointment {

	@Id
	@GeneratedValue
	Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	Patient patient;
	
	@ManyToOne(fetch = FetchType.LAZY)
	Consultation consultation;
	
	@GeneratedValue( /*Generado o Estrategia*/)
	Integer position;
}
