package dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import model.User;

public interface UserDao extends PagingAndSortingRepository<User, Integer>{

}
