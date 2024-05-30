package com.project.questapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.questapp.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
