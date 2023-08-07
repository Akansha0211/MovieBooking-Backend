package com.akansha.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akansha.demo.model.User;
import com.akansha.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;

//	@KafkaListener(topics="javamc", groupId="mygroup")
//	public void consumeFromTopic(String message) {
//		System.out.println("Conumer message: " + message);
//	}
	
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		
		List<User> userList = userRepo.findAll();
		if(userList != null && userList.size() >0) {
			return userList;
		}
		return null;
	}

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		
		if(user != null) {
			return userRepo.saveAndFlush(user);
		}
		return null;
	}

	@Override
	public boolean loginUser(String username, String password) {
		// TODO Auto-generated method stub
		
		User user = userRepo.validateUser(username, password);
		if(user != null) {
			return true;
		}
		return false;
	}
}
