package com.stacksimplify.restservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stacksimplify.restservices.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	
	//Any field you want to return by then use first letter capital
	//findBy<Field_Name> 
	//Creating a custom method returning object User type
	User findByssn(String ssn); //this by just naming the method as findBy...knows to connect to field ssn
	
	User findByUsername(String user_name);
	

}
