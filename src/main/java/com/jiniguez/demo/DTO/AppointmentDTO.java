package com.jiniguez.demo.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AppointmentDTO extends DTO {

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
