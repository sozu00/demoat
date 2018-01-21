package com.jiniguez.demo.DTO;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PatientDTO extends DTO{

	private static final long serialVersionUID = 7644242682821389921L;

	public PatientDTO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	private Integer id;

	private String name;
	
	private List<String> appointments = new ArrayList<>();
	
}
