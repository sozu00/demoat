package com.jiniguez.demo.DTO;

import java.util.List;

import lombok.Data;

@Data
public class ClinicDTO {

	private Integer id;
	
	private List<Integer> rooms;
}
