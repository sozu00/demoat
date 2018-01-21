package com.jiniguez.demo.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiniguez.demo.Model.Turn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ConsultationDTO extends DTO{

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
