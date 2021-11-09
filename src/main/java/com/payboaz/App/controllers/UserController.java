package com.payboaz.App.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payboaz.App.dtos.UserRegistrationDTO;
import com.payboaz.App.models.UserModel;
import com.payboaz.App.services.UserServices;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	private @Autowired UserServices services;
	
	@PostMapping("/save")
	public ResponseEntity<UserModel> newUser(@Valid @RequestBody UserRegistrationDTO newUser){
		return services.registerUser(newUser);
	}

}
