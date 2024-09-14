package com.imaginnovate.emp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "USERDETAILS")
@Data
public class User {

	@Id
	@Column(name = "USERID")
	@GeneratedValue
	private Integer userId;

	@Column(name = "USERNAME")
	private String userName;
	
	@Column(name = "PASSWORD")
	private String passWord;
	
	@Column(name = "FULL_NAME")
	private String fullName;
}
