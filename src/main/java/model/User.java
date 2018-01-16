package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity //Esta clase es una entidad, una tabla de BD
//@Table(name = "usuario") 
//Para asignar el nombre de tabla en castellano
public class User {
	
	@Id //ID de la tabla
	@GeneratedValue
	Integer id;
	
	String name;
	
	@OneToMany
	List<Film> films = new ArrayList<Film>();
}
