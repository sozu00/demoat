package com.jiniguez.demo.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.jiniguez.demo.Model.Turn;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConsultationDTO implements Serializable{

	private static final long serialVersionUID = 5058749307517290114L;

	public ConsultationDTO(Integer id, String day, Turn turn) {
		super();
		this.id=id;
		this.day = day;
		this.turn = turn;
	}
	private Integer id;
	
//	private List<Integer> appointments = new ArrayList<>();

	private Integer doctor_id;
	
	private Integer room_id;
	
	private String day;
	
	private Turn turn;
	
}
