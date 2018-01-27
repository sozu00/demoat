package com.jiniguez.demo.DTO;

import java.io.Serializable;

import lombok.Data;

@Data
public class ClinicDTO implements Serializable{

	private static final long serialVersionUID = 952938686943556234L;

	private Integer id;
	
	private String name;
	
}
