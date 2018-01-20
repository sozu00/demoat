package com.jiniguez.demo.DTO;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DoctorDTO {
	
	private Integer id;
	
	private String name;
	
	private String email;
	
//	private List<Integer> consultations = new ArrayList<>();
}
