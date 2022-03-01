package com.faiz.crud.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faiz.crud.api.models.UserDetails;
import com.faiz.crud.api.repositories.UserRepositories;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserRepositories userRepositories;
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDetails>> getAllUser()
	{
		return ResponseEntity.ok(userRepositories.findAll());
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/users/{id}")
	public ResponseEntity getUserById(@PathVariable(value = "id") int userId)
	{
		try {
			UserDetails userDetails = userRepositories.findById(userId).orElseThrow(null);
			return ResponseEntity.ok(userDetails);
		}
		catch (Exception e) {
			return ResponseEntity.internalServerError().body("No user with given Id");
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/users")
	public ResponseEntity createUser(@RequestBody UserDetails newUserDetails)
	{
		if(newUserDetails.getUserId()<=0)
		{
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User Id cannot be negative"); 
		}
		userRepositories.save(newUserDetails);
		return ResponseEntity.ok(newUserDetails);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/users/{id}")
	public ResponseEntity  updateUser(@PathVariable(value = "id") int userId , @RequestBody UserDetails updatedUserDetails)
	{
		if(updatedUserDetails.getUserId()<=0)
		{
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User Id cannot be negative"); 
		}
		userRepositories.save(updatedUserDetails);
		return ResponseEntity.ok(updatedUserDetails);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/users/{id}")
	public ResponseEntity deleteUser(@PathVariable(value = "id") int userId)
	{
		try {
			
			UserDetails deletedUserDetails = userRepositories.findById(userId).orElseThrow(null);
			userRepositories.deleteById(userId);
			return ResponseEntity.ok(deletedUserDetails);
		}
		catch (Exception e){
			
			return ResponseEntity.internalServerError().body("No user with given Id");
		}
	}
}
