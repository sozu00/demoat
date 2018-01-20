package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Patient {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="patient")
	private List<Appointment> appointments = new ArrayList<Appointment>();
}
