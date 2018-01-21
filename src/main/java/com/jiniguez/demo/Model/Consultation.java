package com.jiniguez.demo.Model;

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

import lombok.Data;

@Entity
@Data
public class Consultation {

	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="consultation")
	private List<Appointment> appointments = new ArrayList<Appointment>();

	@ManyToOne(fetch = FetchType.LAZY)
	private Doctor doctor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Room room;
	
	@Temporal(value = TemporalType.DATE)
	private Date day;
	
	@Enumerated(EnumType.STRING)
	private Turn turn;
	
	public String toString(){
		return String.format("Consultation [id=%d]", id);
	}
}
