package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Medico {

	@Id
	@GeneratedValue
	Integer id;
	
	@Column(unique=true)
	String name;
	
	@Column(unique=true)
	String email;
	
	
}
