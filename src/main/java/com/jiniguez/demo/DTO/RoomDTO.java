package com.jiniguez.demo.DTO;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RoomDTO extends DTO{

	private static final long serialVersionUID = 8658268426689959660L;

	public RoomDTO(Integer id, Integer roomNumber) {
		super();
		this.id = id;
		this.roomNumber = roomNumber;
	}
	
	private Integer id;
	
	private Integer roomNumber;
	
	private String clinic;
	
	private List<String> consultations = new ArrayList<>();

}
