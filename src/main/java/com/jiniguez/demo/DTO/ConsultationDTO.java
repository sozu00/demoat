package com.jiniguez.demo.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiniguez.demo.Model.Turn;

import lombok.Data;

@Data
public class ConsultationDTO {

	private Integer id;
	
//	private List<Integer> appointments = new ArrayList<>();

	private Integer doctor;
	
	private Integer room;
	
//	private Date day;
	
//	private Turn turn;
}
