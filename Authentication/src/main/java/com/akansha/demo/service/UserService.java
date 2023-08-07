package com.akansha.demo.service;

import java.util.List;

import com.akansha.demo.model.User;

public interface UserService {

	public List<User> getAllUsers();
	public User addUser(User user); // registration of user
	public boolean loginUser(String username, String password);
}
