package com.bookstore.services;

import com.bookstore.entities.User;
import com.bookstore.exception.UserNotFoundException;

public interface ILoginService {

User add(User user) throws UserNotFoundException;
	
	User delete(User user) throws UserNotFoundException;
	
	User validate(int id, String pass) throws UserNotFoundException;

	
	
}
