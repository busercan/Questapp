package com.project.questapp.controllers;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.entities.Users;
import com.project.questapp.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	private UsersService usersService;
	
	public UsersController(UsersService usersService) {
		this.usersService = usersService;
	}

	@GetMapping
	public List<Users> getAllUsers(){
		return usersService.getAllUsers();
	}
	
	@PostMapping
	public Users createUsers(@RequestBody @NonNull Users newUsers){
		return usersService.saveOneUsers(newUsers);
	}
	
	@GetMapping("/{userId}")
	public Users getOneUser(@PathVariable @NonNull Long userId) {
		return usersService.getOneUser(userId);
	}
	
	@PutMapping("/{userId}")
	public Users updateOneUser(@PathVariable @NonNull Long userId, @RequestBody Users newUsers) {
		return usersService.updateOneUser(userId,newUsers);
	}	
		
	@DeleteMapping("/{userId}")
	public void deleteOneUser(@PathVariable @NonNull Long userId) {
		usersService.deleteById(userId);
	}
	
}
