package service;

import java.util.List;

import org.springframework.stereotype.Service;

import DTO.UserDTO;
import model.User;

@Service
public interface UserService {

	/**
	 * Busca un usuario por su id
	 * @param id
	 * @return
	 */
	User findById(Integer id);

	/**
	 * Busca todos los usuarios paginados
	 * @param page
	 * @param size
	 * @return
	 */
	List<User> findAll(Integer page, Integer size);

	/**
	 * Crea el usuario u
	 * @param u
	 * @return
	 */
	User create(User u);

	/**
	 * Actualiza el usuario u
	 * @param u
	 */
	void update(User u);

	/**
	 * Elimina al usuario con id=idUser
	 * @param idUser
	 */
	void delete(Integer idUser);
	
	/**
	 * Crea un user a partir de un DTO
	 * @param u
	 * @return
	 */
	User map(UserDTO u);
	
	UserDTO transform(User u);
//	@Query(value = "from User u where u.name like :name")
//	public List<User> findByName(@Param("name") String paramName);
}
