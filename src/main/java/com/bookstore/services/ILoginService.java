package com.bookstore.services;

import com.bookstore.entities.User;
import com.bookstore.exception.UserNotFoundException;

public interface ILoginService {

	User add(User user) throws UserNotFoundException;
	
	User delete(int userId) throws UserNotFoundException;
	
	User validate(String email, String pass) throws UserNotFoundException;	
}
