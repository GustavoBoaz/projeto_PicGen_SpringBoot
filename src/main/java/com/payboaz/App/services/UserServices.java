package com.payboaz.App.services;

import java.nio.charset.Charset;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.payboaz.App.dtos.UserRegistrationDTO;
import com.payboaz.App.models.UserModel;
import com.payboaz.App.repositories.UserRepository;

@Service
public class UserServices {

	private @Autowired UserRepository repository;

	/**
	 * Private static method, used to encrypt with BCryptPasswordEncoder format a
	 * string passed as a parameter.
	 * 
	 * @param password, String format.
	 * @return String
	 * @author Boaz
	 * @since 1.0
	 * @see BCryptPasswordEncoder
	 * 
	 */
	private static String encryptPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

	/**
	 * Private static method, used to generate token in Base64 format.
	 * 
	 * @param email,    String format.
	 * @param password, String format.
	 * @return String
	 * @author Boaz
	 * @since 1.0
	 * @see Base64
	 * 
	 */
	private static String generatorToken(String email, String password) {
		String structure = email + ":" + password;
		byte[] structureBase64 = Base64.encodeBase64(structure.getBytes(Charset.forName("US-ASCII")));
		return new String(structureBase64);
	}

	/**
	 * Public method used to register a user in the system's database. This method
	 * returns a BAD_REQUEST if the intention to register already has an email
	 * registered in the system, to avoid duplication. If you don't hear an existing
	 * email in the system, it returns CREATED status with user object no response.
	 * 
	 * @param newUser, UserRegistrationDTO object.
	 * @return ResponseEntity<UserModel>
	 * @author Boaz
	 * @since 1.0
	 * 
	 */
	public ResponseEntity<UserModel> registerUser(@Valid UserRegistrationDTO newUser) {
		Optional<UserModel> optional = repository.findByEmail(newUser.getEmail());

		if (optional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já em uso!");
		} else {
			UserModel user = new UserModel();
			user.setName(newUser.getName());
			user.setEmail(newUser.getEmail());
			user.setPassword(encryptPassword(newUser.getPassword()));
			user.setToken(generatorToken(newUser.getEmail(), newUser.getPassword()));
			return ResponseEntity.status(201).body(repository.save(user));
		}

	}

}
