package com.jiniguez.demo.DTO;

import java.io.Serializable;
import java.sql.Timestamp;

import com.jiniguez.demo.Model.Turn;

import lombok.Data;

@Data
public class ConsultationDTO implements Serializable{

	private static final long serialVersionUID = 5058749307517290114L;

	private Integer id;
	
	private Integer doctor_internal_id;
	
	private Integer room_id;
	
	private String day;
	
	private Turn turn;
}
