package com.bookstore.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bookstore.entities.User;
import com.bookstore.exception.UserNotFoundException;
import com.bookstore.services.ILoginService;

@RestController
@RequestMapping("/user")
public class LoginRestController {

		@Autowired
		private ILoginService loginService;
		
		//URL: http://localhost:8880/user/add
		@PostMapping(value = "/add", consumes ="application/json") 
		public String addUser(@RequestBody User user) throws UserNotFoundException {
			loginService.add(user);
			return"User added to list";
		}
		
		//URL: http://localhost:8880/user/deleteuser
		@PostMapping(value = "/delete", consumes ="application/json") 
		public String deleteUser(@RequestBody User user) throws UserNotFoundException {
			loginService.delete(user);
			return"User deleted from list";
		}
		
		//URL: http://localhost:8880/user/validate
		@PostMapping(value="/validate" ,produces ="application/json")
		public User validateUser (@RequestBody User user) throws UserNotFoundException  {
			return loginService.validate(user.getUserId(),user.getPassword());
		}
}
