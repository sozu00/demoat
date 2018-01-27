package com.jiniguez.demo.DTO;

import java.io.Serializable;

import lombok.Data;

@Data
public class AppointmentDTO implements Serializable {

	private static final long serialVersionUID = -5786128870273582518L;

	private Integer id;

	private Integer position;
	
	private Integer patient_id;
	
	private Integer consultation_id;

}
