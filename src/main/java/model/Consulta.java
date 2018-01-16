package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Consulta {

	@Id
	@GeneratedValue
	Integer id;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="consulta")
	List<Cita> citas = new ArrayList<Cita>();

	
	@ManyToOne(fetch = FetchType.LAZY)
	Medico medico;
	
	@ManyToOne(fetch = FetchType.LAZY)
	Sala sala;
	
	
}
