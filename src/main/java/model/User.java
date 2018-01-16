package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id //ID de la tabla
	@GeneratedValue
	Integer id;
	
	String name;

 	String email;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	List<Film> films = new ArrayList<Film>();
}
