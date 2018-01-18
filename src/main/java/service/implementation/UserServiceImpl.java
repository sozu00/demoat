package service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

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

	@Override
	public List<User> findAll(Integer page, Integer size) {
		return (List<User>) dao.findAll(new PageRequest(page, size)).getContent();
	}

	@Override
	public User create(User u) {
		return dao.save(u);
	}

	@Override
	public void update(User u) {
		dao.save(u);		
	}

	@Override
	public void delete(Integer idUser) {
		dao.delete(idUser);
	}
}
