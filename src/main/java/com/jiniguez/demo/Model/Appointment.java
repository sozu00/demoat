package com.jiniguez.demo.Model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.dozer.Mapping;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
public class Appointment implements Serializable{

	private static final long serialVersionUID = -1397176648342175197L;

	@Id
	private Integer id;
	
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	private Integer position;
	
    @Mapping("patient_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Patient patient;
	
    @Mapping("consultation_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Consultation consultation;
	
	public String toString() {
//		return String.format("Appointment [id=%d, position=%d]",id, position);
		return id.toString();
	}
}
