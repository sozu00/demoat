package com.jiniguez.demo.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.dozer.Mapping;

import lombok.Data;

@Entity
@Data
public class Room  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6960271996941863570L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(unique = true)
	private Integer roomNumber;
	
	@Mapping("clinic_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Clinic clinic;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="room")
	private List<Consultation> consultations = new ArrayList<Consultation>();

	public String toString(){
//		return String.format("Room [id=%d, roomNumber=%d]",id, roomNumber);
		return id.toString();
	}	
}
