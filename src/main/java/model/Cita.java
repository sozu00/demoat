package model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Cita {

	@Id
	@GeneratedValue
	Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	Paciente paciente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	Consulta consulta;
}
