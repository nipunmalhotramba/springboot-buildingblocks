package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserAlreadyExistsException;
import com.stacksimplify.restservices.exceptions.UserNameNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserServices;

@RestController
@Validated
public class UserController {
	
	@Autowired
	private UserServices UserServices;
	
	//Get all users
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return UserServices.getAllUsers();
	}
	
	/*
	 * //Post - Create a new user
	 * 
	 * @
	 *PostMapping("/users")
	/*
	 * public User createUser(@RequestBody User user_body) { try { return
	 * UserServices.createUser(user_body); } catch (UserAlreadyExistsException ex) {
	 * throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage()); }
	 * 
	 * }
	 */
	
	//Post - Create a new user
	@PostMapping("/users")
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user_body,UriComponentsBuilder builder) {
		try {
			UserServices.createUser(user_body);
			
			HttpHeaders header_txt=new HttpHeaders();
			header_txt.setLocation(builder.path("/users/{id}").buildAndExpand(user_body.getId()).toUri());
			
			return new ResponseEntity<Void>(header_txt,HttpStatus.CREATED);
			
		} catch (UserAlreadyExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
		
	}
	
	//Get - UserById
	@GetMapping("/users/{user_id}")
	public Optional<User> getUserById(@PathVariable("user_id") @Min(1) Long user_id){
		
		try {
			return UserServices.getUserById(user_id);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
	
	//Put - Update UserById [id to update and User object to replace with]
	@PutMapping("/users/{id}")
	public User UpdateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		
		return UserServices.UpdateUserById(id, user);
	}
	
	//Delete - Delete UserById
	@DeleteMapping("/users/{user_PK}")
	public void DeleteUserById(@PathVariable("user_PK") Long user_PK) {
		UserServices.DeleteUserById(user_PK);
	}
	
	/*
	 * //find by ssn
	 * 
	 * @GetMapping("/users/findssn/{ssn}") public User
	 * findByssn(@PathVariable("ssn") String ssn) { return
	 * UserServices.findbyssn(ssn); }
	 */
		
	//find by ssn
	@GetMapping("/users/findssn/{ssn}")
	public User findByssn(@PathVariable("ssn") String ssn) {
		
		try {
			return UserServices.findbyssn(ssn);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
	
	//Get user by username
	@GetMapping("/users/username/{user_name}")
	public User getUserByUsername(@PathVariable("user_name") String user_name) throws UserNameNotFoundException {
		//return UserServices.getUserByUsername(user_name);
		
		User user=UserServices.getUserByUsername(user_name);
		if (user==null)
				throw new UserNameNotFoundException("User name "+user_name+" is not in the User Repository");
		return UserServices.getUserByUsername(user_name);
	}
	
	
}
