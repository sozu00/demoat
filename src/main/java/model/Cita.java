package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Cita {

	@Id
	Integer id;
	
	@ManyToOne
	Paciente paciente;
	
	@ManyToOne
	Consulta consulta;
}
