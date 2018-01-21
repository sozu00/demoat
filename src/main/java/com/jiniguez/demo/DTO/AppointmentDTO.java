package com.jiniguez.demo.DTO;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AppointmentDTO implements Serializable {

	private static final long serialVersionUID = -5786128870273582518L;

	public AppointmentDTO(Integer id, Integer position) {
		super();
		this.id = id;
		this.position = position;
	}
	
	private Integer id;

	private Integer position;
	
	private String patient;
	
	private String consultation;

}
