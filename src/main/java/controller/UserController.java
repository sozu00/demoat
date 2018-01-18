package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import model.User;
import service.UserService;

@Slf4j
@RestController
@RequestMapping(value = "api/user")
public class UserController {
	
	private static final String LOG_INFO_CREATE_USER = "Creando el usuario %i";
	private static final String LOG_INFO_GET_USER_ID = "Recuperando el usuario %i";
	private static final String LOG_INFO_UPDATE_USER_ID = "Recuperando el usuario %i";
	private static final String LOG_INFO_DELETE_USER_ID = "Recuperando el usuario %i";
	private static final String LOG_INFO_GET_ALL_USER = "Recuperando toda la lista de usuarios";
	
	@Autowired
	private UserService userService;
	
	public List<User> findAll(@RequestParam(required=false) Integer page,
			@RequestParam(required = false) Integer size){
		log.info(LOG_INFO_GET_ALL_USER);
		return userService.findAll(page, size);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/(idUser)")
	public User findOneById(@PathVariable Integer idUser) {
		log.info(String.format(LOG_INFO_GET_USER_ID, idUser));
		return userService.findById(idUser);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public User create(@RequestBody User u) {
		log.info(String.format(LOG_INFO_CREATE_USER, u.getId()));
		return userService.create(u);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/(idUser)")
	public void update(@RequestBody User u, @PathVariable Integer idUser) {
		log.info(String.format(LOG_INFO_UPDATE_USER_ID, u.getId()));
		userService.update(u);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/(idUser)")
	public void delete(@RequestBody User u, @PathVariable Integer idUser) {
		log.info(String.format(LOG_INFO_DELETE_USER_ID, u.getId()));
		userService.delete(idUser);
	}
	
}
