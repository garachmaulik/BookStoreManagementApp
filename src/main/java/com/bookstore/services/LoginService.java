package com.bookstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entities.User;
import com.bookstore.exception.UserNotFoundException;
import com.bookstore.repositories.ILoginRepository;

@Service
public class LoginService implements ILoginService {
	
	@Autowired
	private ILoginRepository iLoginRepository;
	
	@Override
	public User add(User user) throws UserNotFoundException {
		if(user == null)
		{
			throw new UserNotFoundException("User is null");
		}
		iLoginRepository.save(user);
		return user;
	}

	@Override
	public User delete(User user) throws UserNotFoundException {
		if(!iLoginRepository.existsById(user.getUserId()))
		{
			throw new UserNotFoundException("User is null");
		}
		iLoginRepository.delete(user);
		return user;
	}

	@Override
	public User validate(int id,String pass) throws UserNotFoundException {
		User user= iLoginRepository.validate( id , pass);
		return user;
	}
}
