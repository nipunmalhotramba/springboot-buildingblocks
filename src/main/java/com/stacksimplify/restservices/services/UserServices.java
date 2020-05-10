package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.repositories.UserRepository;

@Service
public class UserServices {
	
	@Autowired
	private UserRepository UserRepository;
	
	//Get Request - Return all users from Repository which has connection to Database
	public List<User> getAllUsers(){
		return UserRepository.findAll();
	}
	
	// Post request - Send the User (Bean) JSON to Repository to save in database
	// We are also returning the type as User (Bean)
	public User createUser(User user_body) {
		return UserRepository.save(user_body);
	}
	
	//Get UserById
	public Optional<User> getUserById(Long user_by_id){
		Optional<User> user_details=UserRepository.findById(user_by_id); //implicitly this ties to "id" field of User table
		return user_details;
	}
	
	//Update UserById - Return the User object
	public User UpdateUserById(Long user_id, User user) {
		user.setId(user_id); //Sets the persistence context to the id being requested to be updated
		return UserRepository.save(user);
	}
	
	//Delete UserbyId
	public void DeleteUserById(Long pass_PK) {
		if(UserRepository.findById(pass_PK).isPresent()) {
			UserRepository.deleteById(pass_PK);
		}
	}
	
	//find by ssn
	public User findbyssn(String ssn) {
		return UserRepository.findByssn(ssn);
	}
	
}
