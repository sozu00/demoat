package com.jiniguez.demo.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Doctor  implements Serializable{

	private static final long serialVersionUID = -3679617798573006151L;

	@Id
	@GeneratedValue
	@Column(name = "id") //Para acceder desde las querys
	private Integer id;
	
	private String name;
	
	private String email;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="doctor")
	private List<Consultation> consultations = new ArrayList<Consultation>();
	
	public String toString(){
		return id.toString();
	}
}
