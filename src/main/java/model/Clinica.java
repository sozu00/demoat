package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Clinica {

	@Id
	@GeneratedValue
	Integer id;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="clinica")
	List<Sala>salas = new ArrayList<Sala>();
}
