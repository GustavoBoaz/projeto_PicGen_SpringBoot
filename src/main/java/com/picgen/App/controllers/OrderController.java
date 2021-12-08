package com.picgen.App.controllers;

import javax.validation.Valid;

import com.picgen.App.dtos.OrderPaymentDTO;
import com.picgen.App.dtos.OrderRegistrationDTO;
import com.picgen.App.services.OrderServices;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/order")
@Api(tags = "Order Controller API", description = "Order features")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class OrderController {
	
	private @Autowired OrderServices services;
	
	@ApiOperation(value = "New order")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "New order created on the system."),
			@ApiResponse(code = 400, message = "Request error, invalid token!") })
	@PostMapping("/{token}")
	public ResponseEntity<OrderPaymentDTO> newOrder(@PathVariable(value = "token") String token,
			@Valid @RequestBody OrderRegistrationDTO newOrder) {
		return services.newPaymentOrder(token, newOrder);
	}
	
	@ApiOperation(value = "Get order")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Order data!"),
			@ApiResponse(code = 400, message = "Request error, invalid token or idOrder!") })
	@GetMapping("/{token}/{id_order}")
	public ResponseEntity<OrderPaymentDTO> getOrder(@PathVariable(value = "token") String token,
			@PathVariable(value = "id_order") Long idOrder) {
		return services.getPaymentOrder(token, idOrder);
	}
	
	@ApiOperation(value = "Get order by id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Order data!"),
			@ApiResponse(code = 400, message = "Request error, invalid idOrder!") })
	@GetMapping("/{id_order}")
	public ResponseEntity<OrderPaymentDTO> getOrderById(@PathVariable(value = "id_order") Long idOrder) {
		return services.getPaymentOrderById(idOrder);
	}
	
	@ApiOperation(value = "Delete order")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Deleted order"),
			@ApiResponse(code = 400, message = "Request error, invalid token or idOrder!") })
	@DeleteMapping("/{token}/{id_order}")
	public ResponseEntity<Object> deleteOrder(@PathVariable(value = "token") String token,
			@PathVariable(value = "id_order") Long idOrder) {
		return services.deletePaymentOrder(token, idOrder);
	}

}
