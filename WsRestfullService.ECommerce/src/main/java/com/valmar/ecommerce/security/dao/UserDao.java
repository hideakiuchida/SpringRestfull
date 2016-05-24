package com.valmar.ecommerce.security.dao;

import com.valmar.ecommerce.security.model.User;

public interface UserDao {
	public int validateUser(String username, String password);	
	public User getUserById(int userId);
	public User findByUsername(String username);
	
}
