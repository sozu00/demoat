package service;

import org.springframework.stereotype.Service;

import model.User;

@Service
public interface UserService {

	/**
	 * Busca un usuario por su id
	 * @param id
	 * @return
	 */
	User findById(Integer id);
	
//	@Query(value = "from User u where u.name like :name")
//	public List<User> findByName(@Param("name") String paramName);
}
