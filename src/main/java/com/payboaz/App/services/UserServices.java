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

import com.payboaz.App.dtos.UserCredentialsDTO;
import com.payboaz.App.dtos.UserLoginDTO;
import com.payboaz.App.dtos.UserRegistrationDTO;
import com.payboaz.App.dtos.UserUpdateDTO;
import com.payboaz.App.models.UserModel;
import com.payboaz.App.repositories.UserRepository;

@Service
public class UserServices {

	private @Autowired UserRepository repository;
	private UserModel user;
	private UserCredentialsDTO credentials;

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
	 * Private static method, used to generate Basic token in Base64 format.
	 * 
	 * @param email,    String format.
	 * @param password, String format.
	 * @return String
	 * @author Boaz
	 * @since 1.0
	 * @see Base64
	 * 
	 */
	private static String generatorBasicToken(String email, String password) {
		String structure = email + ":" + password;
		byte[] structureBase64 = Base64.encodeBase64(structure.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(structureBase64);
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

	/**
	 * Public method used to get credentials from an administrator user in the
	 * system database. This method returns a BAD_REQUEST if intent is provided with
	 * invalid email or password. If you don't hear invalid email or password, the
	 * system returns CREATED status with UserCredentialsDTO object filled in in the
	 * response.
	 * 
	 * @param userAuth, UserLoginDTO object.
	 * @return ResponseEntity<UserCredentialsDTO>
	 * @author BOAZ
	 * @since 1.0
	 * 
	 */
	public ResponseEntity<UserCredentialsDTO> getCredentials(@Valid UserLoginDTO userAuth) {
		return repository.findByEmail(userAuth.getEmail()).map(resp -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			if (encoder.matches(userAuth.getPassword(), resp.getPassword())) {
				
				UserCredentialsDTO credentials = new UserCredentialsDTO();
				
				credentials.setIdUser(resp.getIdUser());
				credentials.setName(resp.getName());
				credentials.setEmail(resp.getEmail());
				credentials.setPassword(resp.getPassword());
				credentials.setToken(resp.getToken());
				credentials.setAuthorizationBasic(generatorBasicToken(userAuth.getEmail(), userAuth.getPassword()));
				
				return ResponseEntity.status(201).body(credentials);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha incorreta!");
			}
		}).orElseGet(() -> {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email não existe!");
		});
	}

	/**
	 * Public method used to retrieve admin user profile in the system.
	 * 
	 * @param token, String format
	 * @return ResponseEntity<UserModel>
	 * @author Boaz
	 * @since 1.0
	 * 
	 */
	public ResponseEntity<UserModel> getProfile(String token) {
		return repository.findByToken(token).map(resp -> {
			return ResponseEntity.ok(resp);
		}).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token invalido!");
		});
	}
	
	/**
	 * Public method used to update user admin credentials. This method returns a
	 * BAD_REQUEST if idUser or Token are invalid or do not belong to the same user
	 * admin. Otherwise it returns a UserCredentialsDTO with CREATED status and
	 * updates the fields: name, email and password.
	 * 
	 * @param userUpdate, UserUpdateDTO object.
	 * @return ResponseEntity<UserCredentialsDTO>
	 * @author Boaz
	 * @since 1.0
	 * 
	 */
	public ResponseEntity<UserCredentialsDTO> putCredentials(@Valid UserUpdateDTO userUpdate) {
		Optional<UserModel> optional = repository.findByToken(userUpdate.getToken());
		return repository.findById(userUpdate.getIdUser()).map(resp -> {
			if (optional.isPresent() && optional.get().getToken().equals(resp.getToken())) {
				resp.setName(userUpdate.getName());
				resp.setEmail(userUpdate.getEmail());
				resp.setPassword(encryptPassword(userUpdate.getPassword()));
				resp.setToken(generatorToken(userUpdate.getEmail(), userUpdate.getPassword()));

				user = repository.save(resp);
				credentials = new UserCredentialsDTO();
				credentials.setIdUser(user.getIdUser());
				credentials.setName(user.getName());
				credentials.setEmail(user.getEmail());
				credentials.setPassword(user.getPassword());
				credentials.setToken(user.getToken());
				credentials.setAuthorizationBasic(generatorBasicToken(userUpdate.getEmail(), userUpdate.getPassword()));

				return ResponseEntity.status(201).body(credentials);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token invalido!");
			}
		}).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id invalido!");
		});
	}

}
