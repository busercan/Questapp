package com.project.questapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.project.questapp.entities.Users;
import com.project.questapp.repos.UsersRepository;

@Service
public class UsersService {
	
	private UsersRepository usersRepository;

	public UsersService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	public List<Users> getAllUsers(){
		return usersRepository.findAll();
	}
	
	public Users saveOneUsers(@NonNull Users newUsers) {
		return usersRepository.save(newUsers);
	}

	public Users getOneUser(@NonNull Long userId) {
		return usersRepository.findById(userId).orElse(null);
	}

	public Users updateOneUser(@NonNull Long userId, Users newUsers) {
		Optional<Users> users = usersRepository.findById(userId);
		if(users.isPresent()) {
			Users foundUser=users.get();
			foundUser.setUserName(newUsers.getUserName());
			foundUser.setPassword(newUsers.getPassword());
			usersRepository.save(foundUser);
			return foundUser;
		}else
			return null;
	}

	public void deleteById(@NonNull Long userId) {
		usersRepository.deleteById(userId);
		
	}
	

}
