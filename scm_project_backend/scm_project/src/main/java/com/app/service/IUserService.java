package com.app.service;

import java.util.List;

import com.app.pojos.User;

public interface IUserService {

	void saveUser(User user);
	
	List<User> getAllUsers();
	
	User getUserById(long id);
	
	String deleteUserById(long id);
	
	User getUserByEmail(String email);

	void updateProfile(User userSession, String password, String email);
	
}
