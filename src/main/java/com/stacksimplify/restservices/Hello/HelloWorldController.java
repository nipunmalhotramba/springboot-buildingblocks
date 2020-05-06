package com.stacksimplify.restservices.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {
	
	//URI- /helloWorld
	//GET - @RequestMapping or @GetMapping are two ways
	//@RequestMapping(method=RequestMethod.GET, path="/helloWorld")
	@GetMapping("/helloworld_get")
	
	//Method - with return type as String
	public String HelloWorld() {
		return "HelloWorld using GetMapping with GET method and URI=/helloworld_get";
	}
	
	@GetMapping("/helloworld_bean")	
	public UserDetails HelloWorldBean() {
		return new UserDetails("Nipun","Malhotra","Eagan");
	}
}
