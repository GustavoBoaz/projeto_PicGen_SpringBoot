package com.payboaz.App.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.payboaz.App.dtos.UserRegistrationDTO;
import com.payboaz.App.models.UserModel;
import com.payboaz.App.services.UserServices;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {

	private @Autowired TestRestTemplate testRest;
	private @Autowired UserServices services;

	@Test
	@Order(1)
	@DisplayName("Cadastra novo Usuario")
	void deveCriarUmUsuario() {

		HttpEntity<UserRegistrationDTO> request = new HttpEntity<UserRegistrationDTO>(
				new UserRegistrationDTO("Lauro Boaz", "lauro@email.com", "134652"));

		ResponseEntity<UserModel> response = testRest
				.exchange("/api/v1/user/save",HttpMethod.POST, request, UserModel.class);
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(request.getBody().getEmail(), response.getBody().getEmail());
		
	}

}
