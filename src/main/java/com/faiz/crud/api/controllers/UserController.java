package com.faiz.crud.api.controllers;

import java.util.List;

import com.faiz.crud.api.models.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faiz.crud.api.repositories.UserRepositories;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserRepositories userRepositories;
	
	@GetMapping("/users")
	public ResponseEntity<List<UserInformation>> getAllUser()
	{
		return ResponseEntity.ok(userRepositories.findAll());
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/users/{userName}")
	public ResponseEntity getUserById(@PathVariable(value = "userName") String userName)
	{
		try {
			UserInformation userDetails = userRepositories.findById(userName).orElseThrow(null);
			return ResponseEntity.ok(userDetails);
		}
		catch (Exception e) {
			return ResponseEntity.internalServerError().body("No user with given Id");
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/users/{userName}")
	public ResponseEntity  updateUser(@PathVariable(value = "userName") String userName , @RequestBody UserInformation updatedUserDetails)
	{
		userRepositories.save(updatedUserDetails);
		return ResponseEntity.ok(updatedUserDetails);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/users/{userName}")
	public ResponseEntity deleteUser(@PathVariable(value = "userName") String userName)
	{
		try {

			UserInformation deletedUserDetails = userRepositories.findById(userName).orElseThrow(null);
			userRepositories.deleteById(userName);
			return ResponseEntity.ok(deletedUserDetails);
		}
		catch (Exception e){
			
			return ResponseEntity.internalServerError().body("No user with given Id");
		}
	}
}
