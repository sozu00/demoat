package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Doctor {

	@Id
	@GeneratedValue
	Integer id;
	
	String name;
	
	@Column(unique=true)
	String email;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="medico")
	List<Consultation> consultas = new ArrayList<Consultation>();
}
