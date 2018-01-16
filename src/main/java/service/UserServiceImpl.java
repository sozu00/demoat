package service;

import dao.UserDao;
import model.User;

public class UserServiceImpl implements UserService {

	
	UserDao dao;
	
	@Override
	public User findById(Integer id) {
		return dao.findOne(id);
	}
}
