package com.jiniguez.demo.DTO;

import java.io.Serializable;

import lombok.Data;

@Data
public class DoctorDTO implements Serializable{
	
	private static final long serialVersionUID = 7315412479801388757L;

	private Integer id;
	
	private String name;
	
	private String email;
}
