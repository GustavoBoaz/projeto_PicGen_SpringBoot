package com.picgen.App.controllers;

import javax.validation.Valid;

import com.picgen.App.dtos.UserCredentialsDTO;
import com.picgen.App.dtos.UserLoginDTO;
import com.picgen.App.dtos.UserRegistrationDTO;
import com.picgen.App.dtos.UserUpdateDTO;
import com.picgen.App.models.UserModel;
import com.picgen.App.services.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(allowedHeaders = "*", origins = "*")
@Api(tags = "User Controller API", description = "User utilities")
public class UserController {
	
	private @Autowired UserServices services;
	
	@ApiOperation(value = "Create user")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "New user created on the system."),
			@ApiResponse(code = 400, message = "Request error, email already in use!")
	})
	@PostMapping("/save")
	public ResponseEntity<UserModel> newUser(@Valid @RequestBody UserRegistrationDTO newUser){
		return services.registerUser(newUser);
	}
	
	@ApiOperation(value = "See credentials")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Credentials Returns!"),
			@ApiResponse(code = 400, message = "Request error, invalid email or password!")
	})
	@PutMapping("/credentials")
	public ResponseEntity<UserCredentialsDTO> seeCredentials(@Valid @RequestBody UserLoginDTO userAuth){
		return services.getCredentials(userAuth);
	}
	
	@ApiOperation(value = "Update credentials")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "User Credentials!"),
			@ApiResponse(code = 400, message = "Request error, invalid token or idUser!")
	})
	@PutMapping("/credentials/update")
	public ResponseEntity<UserCredentialsDTO> putCredentials(@Valid @RequestBody UserUpdateDTO userUpdate){
		return services.putCredentials(userUpdate);
	}
	
	@ApiOperation(value = "Get profile")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "User profile!"),
			@ApiResponse(code = 400, message = "Request error, invalid token!")
	})
	@GetMapping("/{token}")
	public ResponseEntity<UserModel> getProfile(@PathVariable(value = "token") String token){
		return services.getProfile(token);
	}

}
