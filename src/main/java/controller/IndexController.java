package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.User;
import service.UserService;

@RestController
public class IndexController {

	@Autowired
	UserService userService;
	
	
	@RequestMapping("/")
	public User findUser() {
		return userService.findById(1);
	}
}
