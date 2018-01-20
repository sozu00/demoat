package DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import model.Turn;

@Data
public class ConsultationDTO {

	private Integer id;
	
	private List<String> appointments = new ArrayList<>();

	private Integer doctor;
	
	private Integer room;
	
	private Date day;
	
	private Turn turn;
}
