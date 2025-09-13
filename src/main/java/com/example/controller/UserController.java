package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgres.User;

@RestController
@RequestMapping("/users")
public class UserController {

	public class UserProperty {

	}

//	@Autowired
//	private UserProperty userProerty;
//	
//	@GetMapping
//	public List<User> getAllUsers(){
//		return userProerty.findAll();
//	}
//	
//	@PostMapping
//    public User createUser(@RequestBody User user) {
//        return userRepository.save(user);
//    }
}
