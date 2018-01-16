package service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import dao.UserDao;
import model.User;

public class UserServiceImpl implements UserService {

	
	UserDao dao;
	
	@Override
	public User findById(Integer id) {
		return dao.findOne(id);
	}
}
