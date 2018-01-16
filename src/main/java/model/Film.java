package model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Film {
	
	@Id
	private
	String id;
	
	private String title;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

}
