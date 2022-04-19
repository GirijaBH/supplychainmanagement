package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.customexceptionhandler.ResourceNotFoundException;
import com.app.pojos.User;
import com.app.repository.IUserRepository;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Autowired 
	private IUserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void saveUser(User user) {
		userRepo.save(user);
		
	}

	@Override
	public List<User> getAllUsers() {
	
		return userRepo.findAll();
	}


	@Override
	public String deleteUserById(long id) {
		userRepo.deleteById(id);
		return "User Deleted Successfully!";

	}

	@Override
	public User getUserById(long id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("No Such User Present!"));
		
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepo.findByEmail(email);
	}

	@Override
	public void updateProfile(User user, String password, String email) {
		
		String userEmail = user.getEmail();
		boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));
		
		if (isEmailChanged) {
            user.setEmail(email);
        }
		
		user.setPassword(passwordEncoder.encode(password));
		userRepo.save(user);
	}



}
