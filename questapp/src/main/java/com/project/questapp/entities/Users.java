package com.project.questapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="users")
@Data
public class Users {
	
	@Id
	Long id;
	
	@Column
	String userName;
	@Column
	String password;
	
}
