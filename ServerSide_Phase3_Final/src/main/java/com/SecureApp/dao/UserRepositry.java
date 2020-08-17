package com.SecureApp.dao;

import com.SecureApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepositry extends JpaRepository<User, Long> {

	//User findByUsername(String userName);
	
	User findByUsername(String userName);
	
	User findByUsernameOrEmail(String userName,String email);
	
	User findById(long id);
	
	List<User> getAllByAuthority(String authority);
	
	boolean existsByUsername(String username);
	
}
