package com.jiniguez.demo.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Patient {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="patient")
	private List<Appointment> appointments = new ArrayList<Appointment>();
	
	public String toString(){
		return String.format("Patient [id=%d, name=%s]",id, name);
	}
}
