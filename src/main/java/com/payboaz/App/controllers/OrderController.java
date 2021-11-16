package com.payboaz.App.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/{token}/{id_order}")
	public ResponseEntity<OrderPaymentDTO> getOrder(@PathVariable(value = "token") String token,
			@PathVariable(value = "id_order") Long idOrder) {
		return services.getPaymentOrder(token, idOrder);
	}
	
	@DeleteMapping("/{token}/{id_order}")
	public ResponseEntity<Object> deleteOrder(@PathVariable(value = "token") String token,
			@PathVariable(value = "id_order") Long idOrder) {
		return services.deletePaymentOrder(token, idOrder);
	}

}
