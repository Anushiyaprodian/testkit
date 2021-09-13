package com.prodian.SpringBootCrudOperationServices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prodian.SpringBootCrudOperationServices.entity.User;
import com.prodian.SpringBootCrudOperationServices.exception.ResourceNotFoundException;
import com.prodian.SpringBootCrudOperationServices.repository.UserRepository;

@RestController
@RequestMapping("/api/users1")
public class UserController {
	@Autowired
	private UserRepository userrepository;
	//get all users
	@GetMapping
	public List<User> getAllUsers(){
		return this.userrepository.findAll();
	}
	
	//get user by id
	@GetMapping("/{id}")
	public User getUserById(@PathVariable (value ="id") long userId) {
		return this.userrepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not foud with id:"+userId));
	}
	
	//create user
	@PostMapping
	public User createUser(@RequestBody User user) {
		return this.userrepository.save(user);
	}
	//update user
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user,@PathVariable("id") long userId) {
		User existing = this.userrepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not foud with id:"+userId));
		existing.setFirstName(user.getFirstName());
		existing.setLastName(user.getLastName());
		existing.setEmail(user.getEmail());
		return this.userrepository.save(existing);
	}
	//delete user by id
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") long userId){
		User existing = this.userrepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not foud with id:"+userId));
		this.userrepository.delete(existing);
		return ResponseEntity.ok().build();
	}
}
