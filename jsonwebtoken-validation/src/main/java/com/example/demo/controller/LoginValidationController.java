package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AuthRequest;
import com.example.demo.entity.JwtResponse;
import com.example.demo.entity.LoginStatus;
import com.example.demo.util.JwtUtil;

//Author : SUBASH BERDINANT
@RestController
@CrossOrigin
public class LoginValidationController {

	@Autowired private JwtUtil jwtUtil;

	@Autowired private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;


	@GetMapping
	public String getUserLogin() {
		return "subash pernandas";
	}

	/*
	 * @PostMapping("/authenticate") public ResponseEntity<LoginStatus>
	 * generateWebToken(@RequestBody AuthRequest authReqest) throws Exception {
	 * LoginStatus loginStatus = new LoginStatus();
	 * loginStatus.setMessage("Bad Credential Do not Match"); try {
	 * authenticationManager.authenticate(new
	 * UsernamePasswordAuthenticationToken(authReqest.getUserName(),
	 * authReqest.getPassword())); } catch(Exception e) { throw new
	 * Exception("invalid username/password"); }
	 * 
	 * String jsonWebToken = jwtUtil.generateToken(authReqest.getUserName());
	 * if(jsonWebToken != null) { loginStatus.setMessage("Logged in Successfully");
	 * loginStatus.setToken(jwtUtil.generateToken(authReqest.getUserName())); return
	 * new ResponseEntity<LoginStatus>(loginStatus, HttpStatus.OK); } return new
	 * ResponseEntity<LoginStatus>(loginStatus, HttpStatus.UNAUTHORIZED); }
	 */

	@PostMapping(value = "/authenticate")
	public String createAuthenticationToken(@RequestBody AuthRequest authReqest) throws Exception {

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReqest.getUserName(), 
				authReqest.getPassword()));

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authReqest.getUserName());

		final String token = jwtUtil.generateToken(userDetails);

		return token;
				//ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
