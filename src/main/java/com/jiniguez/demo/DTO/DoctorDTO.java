package com.jiniguez.demo.DTO;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
	
	private Integer id;
	
	private String name;
	
	private String email;
	
	
//	private List<Integer> consultations = new ArrayList<>();
}
