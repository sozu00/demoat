package com.jiniguez.demo.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Doctor {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
//	@Column(unique=true)
	private String email;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="doctor")
	private List<Consultation> consultations = new ArrayList<Consultation>();
}
