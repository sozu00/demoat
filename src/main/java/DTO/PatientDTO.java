package DTO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class PatientDTO {

	private Integer id;

	private String name;
	
	private List<String> appointments = new ArrayList<>();
}
