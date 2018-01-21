package com.jiniguez.demo.DTO;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DoctorDTO extends DTO{
	
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
