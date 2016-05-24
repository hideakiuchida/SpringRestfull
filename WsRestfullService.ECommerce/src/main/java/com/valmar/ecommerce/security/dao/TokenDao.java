package com.valmar.ecommerce.security.dao;

import com.valmar.ecommerce.security.model.User;

public interface TokenDao {
	public String generateToken(User user);
	public boolean validateToken(String tokenId);
	public String getUsernameFromToken(String token);
}
