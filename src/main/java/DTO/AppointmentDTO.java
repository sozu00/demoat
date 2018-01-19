package DTO;

import lombok.Data;

@Data
public class AppointmentDTO {

	private Integer id;
	
	private Integer pacientId;
	
	private Integer consultationId;
	
	private Integer position;
}
