package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Paciente {

	@Id
	Integer id;
	
	@OneToMany
	List<Cita> citas = new ArrayList<Cita>();
}
