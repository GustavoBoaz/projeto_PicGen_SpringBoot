package com.payboaz.App.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.payboaz.App.models.UserModel;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
class UserRepositoryTest {
	
	private @Autowired UserRepository repository;
	
	@BeforeAll
	void start() {
		repository.save(new UserModel("Marcelo Boaz", "marcelo@email.com", "134652", "BATATA1"));
		repository.save(new UserModel("Bart Boaz", "bart@email.com", "134652", "BATATA2"));
		repository.save(new UserModel("Bruna Boaz", "bruna@email.com", "134652", "BATATA3"));
		repository.save(new UserModel("Arthur Boaz", "arthur@email.com", "134652", "BATATA4"));
	}

	@Test
	@DisplayName("Retorna 1 Usuario")
	void deveRetornarUmUsuario() {
		UserModel user = repository.findByEmail("marcelo@email.com").get();
		assertTrue(user.getEmail().equals("marcelo@email.com"));
	}
	
	@Test
	@DisplayName("Retorna 4 Usuarios")
	void deveRetornarQuatroUsuarios() {
		List<UserModel> lista = repository.findAllByNameContainingIgnoreCase("Boaz");
		
		assertEquals(4, lista.size());
		assertTrue(lista.get(0).getName().equals("Marcelo Boaz"));
		assertTrue(lista.get(1).getName().equals("Bart Boaz"));
		assertTrue(lista.get(2).getName().equals("Bruna Boaz"));
		assertTrue(lista.get(3).getName().equals("Arthur Boaz"));
	}
	
	

}
