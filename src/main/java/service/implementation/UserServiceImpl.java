package service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;
import model.User;
import service.UserService;


public class UserServiceImpl implements UserService {

	@Autowired
	UserDao dao;
	
	@Override
	public User findById(Integer id) {
		return dao.findOne(id);
	}
}
