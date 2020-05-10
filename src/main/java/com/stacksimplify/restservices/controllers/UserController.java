package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.services.UserServices;

@RestController
public class UserController {
	
	@Autowired
	private UserServices UserServices;
	
	//Get all users
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return UserServices.getAllUsers();
	}
	
	//Post - Create a new user
	@PostMapping("/users")
	public User createUser(@RequestBody User user_body) {
		return UserServices.createUser(user_body);
	}
	
	//Get - UserById
	@GetMapping("/users/{user_id}")
	public Optional<User> getUserById(@PathVariable("user_id") Long user_id){
		return UserServices.getUserById(user_id);
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
	
	//find by ssn
	@GetMapping("/users/findssn/{ssn}")
	public User findByssn(@PathVariable("ssn") String ssn) {
		return UserServices.findbyssn(ssn);
	}
	
	
}
