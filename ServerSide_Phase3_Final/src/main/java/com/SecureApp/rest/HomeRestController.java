package com.SecureApp.rest;

import com.SecureApp.entity.User;
import com.SecureApp.exceptionHander.CouponSystemException;
import com.SecureApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/auth")
public class HomeRestController {

	@Autowired
	UserService userService;

	@PostMapping("/validateLogin")
	public User validateLogin(@RequestBody User userDetails) throws CouponSystemException{
		return userService.validateLogin(userDetails);
	}
	
	
	@GetMapping("/logout")
	public long logout (HttpServletRequest request, HttpServletResponse response) {
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (auth != null){
	    new SecurityContextLogoutHandler().logout(request, response, auth);
	  }
	  return 1;
	}
	

}
