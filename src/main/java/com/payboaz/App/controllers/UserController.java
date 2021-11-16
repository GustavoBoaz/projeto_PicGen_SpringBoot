package com.payboaz.App.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payboaz.App.dtos.UserCredentialsDTO;
import com.payboaz.App.dtos.UserLoginDTO;
import com.payboaz.App.dtos.UserRegistrationDTO;
import com.payboaz.App.dtos.UserUpdateDTO;
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
	
	@PutMapping("/credentials")
	public ResponseEntity<UserCredentialsDTO> seeCredentials(@Valid @RequestBody UserLoginDTO userAuth){
		return services.getCredentials(userAuth);
	}
	
	@PutMapping("/credentials/update")
	public ResponseEntity<UserCredentialsDTO> putCredentials(@Valid @RequestBody UserUpdateDTO userUpdate){
		return services.putCredentials(userUpdate);
	}
	
	@GetMapping("/{token}")
	public ResponseEntity<UserModel> getProfile(@PathVariable(value = "token") String token){
		return services.getProfile(token);
	}

}
