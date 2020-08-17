package com.SecureApp.service;

import com.SecureApp.dao.UserRepositry;
import com.SecureApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepositry repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		String email=username;
		User user = repo.findByUsernameOrEmail(username,email);
		if (user == null) {

			throw new UsernameNotFoundException("User 404");
		}

		return new UserPrincipal(user);
	}

}
