package com.jiniguez.demo.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiniguez.demo.Model.Turn;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConsultationDTO implements Serializable{

	private static final long serialVersionUID = 5058749307517290114L;

	public ConsultationDTO(Integer id, Date day, Turn turn) {
		super();
		this.id=id;
		this.day = day;
		this.turn = turn;
	}
	private Integer id;
	
	private List<String> appointments = new ArrayList<>();

	private String doctor;
	
	private String room;
	
	private Date day;
	
	private Turn turn;
	
}
