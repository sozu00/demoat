package com.jiniguez.demo.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Clinic  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8669217675983589571L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="clinic")
	private List<Room>rooms = new ArrayList<Room>();

	public String toString(){
//		return String.format("Clinic [id=%d]",id);
		return id.toString();
	}

}

