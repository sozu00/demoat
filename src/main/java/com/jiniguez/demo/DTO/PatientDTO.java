package com.jiniguez.demo.DTO;

import java.io.Serializable;

import lombok.Data;

@Data
public class PatientDTO implements Serializable{

	private static final long serialVersionUID = 7644242682821389921L;
	private Integer id;

	private String name;
}
