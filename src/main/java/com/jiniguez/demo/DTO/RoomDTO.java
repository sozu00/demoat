package com.jiniguez.demo.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoomDTO implements Serializable{

	private static final long serialVersionUID = 8658268426689959660L;

	public RoomDTO(Integer id, Integer roomNumber) {
		super();
		this.id = id;
		this.roomNumber = roomNumber;
	}
	
	private Integer id;
	
	private Integer roomNumber;
	
	private Integer clinic_id;
	
//	private List<Integer> consultations = new ArrayList<>();

}
