package com.jiniguez.demo.DTO;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClinicDTO implements Serializable{

	private static final long serialVersionUID = 952938686943556234L;

	public ClinicDTO(Integer id) {
		super(); 
		this.id = id;
	}
	
	private Integer id;
	
	private List<String> rooms;
	
}
