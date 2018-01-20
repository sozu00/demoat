package com.jiniguez.demo.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Clinic {

	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="clinic")
	private List<Room>rooms = new ArrayList<Room>();
}
