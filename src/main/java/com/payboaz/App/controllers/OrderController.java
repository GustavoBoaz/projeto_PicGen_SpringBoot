package com.payboaz.App.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payboaz.App.dtos.OrderPaymentDTO;
import com.payboaz.App.dtos.OrderRegistrationDTO;
import com.payboaz.App.services.OrderServices;

@RestController
@RequestMapping("/api/v1/order")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class OrderController {
	
	private @Autowired OrderServices services;
	
	@PostMapping("/{token}")
	public ResponseEntity<OrderPaymentDTO> newOrder(@PathVariable(value = "token") String token,
			@Valid @RequestBody OrderRegistrationDTO newOrder) {
		return services.newPaymentOrder(token, newOrder);
	}

}
