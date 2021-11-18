package com.payboaz.App.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserModelTest {

	public UserModel user;
	
	@Autowired
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	
	Validator validator = factory.getValidator();
	
	@BeforeEach
	public void start() {
		
		user = new UserModel();
		user.setEmail("fulanoemail.com");
		user.setName("Brenda Boaz");
		user.setPassword("134652");
	}

	@Test
	void validaEmailUsuarioRetornaErro() {
		Set<ConstraintViolation<UserModel>> objeto = validator.validate(user);
		
		assertFalse(objeto.isEmpty());
		
	}
}
