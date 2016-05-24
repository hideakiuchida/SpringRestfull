package com.valmar.ecommerce.security.services;

public interface UserService {

	public int validateUser(String username, String password);
	public String generateToken(int userId);
	public boolean validateToken(String token);
	public String getUsernameFromToken(String token);
}
