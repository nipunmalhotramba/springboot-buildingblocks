package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserAlreadyExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;

@Service
public class UserServices {
	
	@Autowired
	private UserRepository UserRepository;
	
	//Get Request - Return all users from Repository which has connection to Database
	public List<User> getAllUsers(){
		return UserRepository.findAll();
	}
	
	/*
	 * // Post request - Send the User (Bean) JSON to Repository to save in database
	 * // We are also returning the type as User (Bean) 
	 * public User createUser(User user_body) 
	 * { 
	 * 		return UserRepository.save(user_body);
	 *  }
	 */
		
	// Post request - Send the User (Bean) JSON to Repository to save in database
	// We are also returning the type as User (Bean)
	public User createUser(User user_body) throws UserAlreadyExistsException {
		
		//if user exists then throw exception
		if(UserRepository.findByssn(user_body.getSsn())!=null) {
			throw new UserAlreadyExistsException("User already exists in the UserRepository");
		}
		return UserRepository.save(user_body);
	}
	
	//Get UserById
	public Optional<User> getUserById(Long user_by_id) throws UserNotFoundException {
		Optional<User> user_details=UserRepository.findById(user_by_id); //implicitly this ties to "id" field of User table
		
		if (!user_details.isPresent()) {
			throw new UserNotFoundException("User Does not exist which you are trying to find");
		}
		
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
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User does not exist which you are trying to delete");
		}
	}
	
	/*
	 * //find by ssn public User findbyssn(String ssn) { return
	 * UserRepository.findByssn(ssn); }
	 */
	
	//find by ssn
		public User findbyssn(String ssn) throws UserNotFoundException {
		
			Optional<User> return_ssn=Optional.ofNullable(UserRepository.findByssn(ssn));
					if (!return_ssn.isPresent()){
						throw new UserNotFoundException("SSN does not exist!");
					}
			return UserRepository.findByssn(ssn);
		}
}
