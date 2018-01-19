package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Room {

	@Id
	@GeneratedValue
	Integer id;
	
	@Column(unique = true)
	String roomNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	Clinic clinica;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="sala")
	List<Consultation> consultas = new ArrayList<Consultation>();
	
}
