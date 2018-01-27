package com.jiniguez.demo.DTO;

import java.io.Serializable;

import lombok.Data;

@Data
public class RoomDTO implements Serializable{

	private static final long serialVersionUID = 8658268426689959660L;
	
	private Integer id;
	
	private Integer roomNumber;
	
	private Integer clinic_id;
}
