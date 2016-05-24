package com.valmar.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.valmar.ecommerce.aspect.Loggable;
import com.valmar.ecommerce.security.controller.JwtTokenUtil;
import com.valmar.ecommerce.security.model.AuthenticationRequest;
import com.valmar.ecommerce.security.model.AuthenticationResponse;
import com.valmar.ecommerce.security.services.UserService;

@RestController
public class AuthenticationRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    private UserService userService;

    @Loggable
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestHeader("Authorization") String authorization) throws AuthenticationException {

    	AuthenticationRequest authenticationRequest = jwtTokenUtil.getAuthenticationRequest(authorization);
    	int userId = userService.validateUser(authenticationRequest.getUsername(), authenticationRequest.getPassword());
    	if(userId!=0){
    		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );
    		
	        final Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String token = userService.generateToken(userId);
	        
	        // Return the token
	    	return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse(token), HttpStatus.OK);
        }
    	else{
    		return new ResponseEntity<String>("Wrong Crendentials", HttpStatus.UNAUTHORIZED);
    	}    
    	
    }

}
