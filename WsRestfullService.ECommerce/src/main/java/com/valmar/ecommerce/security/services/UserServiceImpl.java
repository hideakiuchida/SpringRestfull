package com.valmar.ecommerce.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valmar.ecommerce.security.dao.TokenDao;
import com.valmar.ecommerce.security.dao.UserDao;
import com.valmar.ecommerce.security.model.User;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private TokenDao tokenDao;

	public int validateUser(String username, String password) {
		return userDao.validateUser(username, password);
	}

	public String generateToken(int userId) {
		User user = userDao.getUserById(userId);
		if(user!=null)
			return tokenDao.generateToken(user);
		else 
			return null;
	}
	
	public boolean validateToken(String token){
		return tokenDao.validateToken(token);
	}
	
	public String getUsernameFromToken(String token){
		return tokenDao.getUsernameFromToken(token);
	}

}
