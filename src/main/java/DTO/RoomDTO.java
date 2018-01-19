package DTO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class RoomDTO {

	Integer id;
	
	String roomNumber;
	
	String clinicId;
	
	List<String> consultations = new ArrayList<>();
	
}
