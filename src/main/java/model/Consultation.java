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
public class Consultation {

	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="consulta")
	private List<Appointment> citas = new ArrayList<Appointment>();

	@ManyToOne(fetch = FetchType.LAZY)
	private Doctor medico;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Room sala;
	
	@Temporal(value = TemporalType.DATE)
	private Date fecha;
	
	@Enumerated(EnumType.STRING)
	private Turn turno;
}
