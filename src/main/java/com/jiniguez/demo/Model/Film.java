package com.jiniguez.demo.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import model.User;

@Entity
public class Film {
	
	@Id
	private
	String id;
	
	String title;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

}
