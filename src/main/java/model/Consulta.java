package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Consulta {

	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="consulta")
	private List<Cita> citas = new ArrayList<Cita>();

	@ManyToOne(fetch = FetchType.LAZY)
	private Medico medico;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Sala sala;
	
	@Temporal(value = TemporalType.DATE)
	private Date fecha;
	
	@Enumerated(EnumType.STRING)
	private Turno turno;
}
