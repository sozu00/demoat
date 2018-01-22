package com.jiniguez.demo.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientDTO implements Serializable{

	private static final long serialVersionUID = 7644242682821389921L;

	public PatientDTO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	private Integer id;

	private String name;
	
//	private List<Integer> appointments = new ArrayList<>();
	
}
