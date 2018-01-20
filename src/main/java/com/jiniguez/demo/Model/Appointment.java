package com.jiniguez.demo.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Appointment {

	@Id
	@GeneratedValue
	private Integer id;
	
	@GeneratedValue
	private Integer position;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Patient patient;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Consultation consultation;
	
}
