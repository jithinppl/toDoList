package com.todolist.controller;

//import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.security.JwtUtil;
import com.todolist.service.AuthenticationService;
import com.todolist.view.AuthenticateView;
import com.todolist.view.UserSigninView;


@RestController
@CrossOrigin(origins="*")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userdetailsservice;
	@Autowired
	private JwtUtil Jwttokenutil;

	@Autowired
	private AuthenticationService authenticationService;

	@RequestMapping(path = "/authentication", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken( @RequestBody UserSigninView userSigninView) throws Exception {
		// Authenticate the user throws exception if not able to to authenticate
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userSigninView.getUserName(), userSigninView.getPassword()));
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(501).body("Incorrect Password");
		}
		final UserDetails userDetails = userdetailsservice.loadUserByUsername(userSigninView.getUserName());
		final String jwt = Jwttokenutil.GenerateToken(userDetails);
		AuthenticateView auth=new AuthenticateView();
		auth.setToken(jwt);
		return ResponseEntity.ok(auth);
	}
}

