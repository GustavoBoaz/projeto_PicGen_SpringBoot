package com.picgen.App.resources;

import com.picgen.App.services.OrderServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class PaymentResources {

    private @Autowired OrderServices services;

    @GetMapping("/{token}/{id_ordem}/PAY")
	public ResponseEntity<Object> payOrder(@PathVariable(value = "token") String token, @PathVariable(value = "id_ordem") Long idOrdem){
		return services.qrCodePayment(token, idOrdem);
	}
    
}
