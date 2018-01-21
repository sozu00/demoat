package com.jiniguez.demo.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DoctorDTO implements Serializable{
	
	private static final long serialVersionUID = 7315412479801388757L;

	public DoctorDTO(Integer id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	private Integer id;
	
	private String name;
	
	private String email;
	
	private List<String> consultations = new ArrayList<>();
	
}
