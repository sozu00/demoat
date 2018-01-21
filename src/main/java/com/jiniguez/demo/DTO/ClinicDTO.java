package com.jiniguez.demo.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ClinicDTO extends DTO{

	private static final long serialVersionUID = 952938686943556234L;

	public ClinicDTO(Integer id) {
		super(); 
		this.id = id;
	}
	
	private Integer id;
	
	private List<String> rooms;
	
}
