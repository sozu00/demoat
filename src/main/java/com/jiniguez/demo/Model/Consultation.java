package com.jiniguez.demo.Model;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.dozer.Mapping;

import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints= @UniqueConstraint(columnNames = {"room_id", "doctor_id", "day", "turn"}))
public class Consultation implements Serializable {
	private static final long serialVersionUID = 8466851773235467553L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="consultation")
	private List<Appointment> appointments = new ArrayList<Appointment>();

	@Mapping("doctor_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Doctor doctor;
	
	@Mapping("room_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Room room;
	
	@Temporal(value = TemporalType.DATE)
	private Date day;
	
	@Enumerated(EnumType.STRING)
	private Turn turn;
	
	public String toString(){
//		return String.format("Consultation [id=%d]", id);
		return id.toString();
	}
}
