package DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import model.Turn;

@Entity
public class ConsultationDTO {

	private Integer id;
	
	private List<String> appointments = new ArrayList<>();

	private String doctor;
	
	private String room;
	
	private Date day;
	
	private Turn turn;
}
