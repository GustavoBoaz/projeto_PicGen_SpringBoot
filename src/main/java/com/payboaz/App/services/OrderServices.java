package com.payboaz.App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.payboaz.App.dtos.OrderRegistrationDTO;
import com.payboaz.App.dtos.OrderPaymentDTO;
import com.payboaz.App.models.OrderModel;
import com.payboaz.App.models.UserModel;
import com.payboaz.App.repositories.OrderRepository;
import com.payboaz.App.repositories.UserRepository;
import com.payboaz.App.utils.QRFactory;

@Service
public class OrderServices {
	
	private @Autowired OrderRepository repositoryOrder;
	private @Autowired UserRepository repositoryUser;
	private OrderModel newOrder;
	private OrderModel order;
	private OrderPaymentDTO objectDTO;
	private static final String urlPayment = "https://payboaz.herokuapp.com/payboaz/page/payment/";
	private static final String qrPayment = "https://payboaz.herokuapp.com/payboaz/";

	
	/**
	 * Static private method of generating QR Code in a format
	 * "data:image/png;base64," accepted in html img tags.
	 * 
	 * @param token,   String format.
	 * @param idOrder, Long format.
	 * @return String
	 * @throws Exception
	 * @author Boaz
	 * @since 1.0
	 *
	 */
	private static String getQRCodeBase64(String token, Long idOrder) throws Exception {
		String qrCodeString = QRFactory
				.conversorPNGToString(QRFactory.generatorQRCode(qrPayment + token + "/" + idOrder + "/PAY"));
		return "data:image/png;base64," + qrCodeString;
	}

	/**
	 * Public method responsible for creating a new payment order in the system.
	 * This method returns BAD_REQUEST when token passed as parameter is invalid.
	 * Otherwise, the method returns an OrderPaymentDTO with status CREATED.
	 * 
	 * @param token,    String format.
	 * @param newOrdem, OrderRegistrationDTO format.
	 * @return ResponseEntity<OrderPaymentDTO>
	 * @author Boaz
	 * @since 1.0
	 * 
	 */
	public ResponseEntity<OrderPaymentDTO> newPaymentOrder(String token, OrderRegistrationDTO newOrdem) {
		return repositoryUser.findByToken(token).map(resp -> {

			try {
				newOrder = new OrderModel(
						newOrdem.getValue(),
						newOrdem.getEmailBuyer(),
						newOrdem.getDocumentBuyer(),
						resp);

				order = repositoryOrder.save(newOrder);

				objectDTO = new OrderPaymentDTO(
						order,
						urlPayment + token + "/" + order.getIdOrder(),
						getQRCodeBase64(token, order.getIdOrder()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ResponseEntity.status(201).body(objectDTO);
		}).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token invalido!");
		});
	}
	
	/**
	 * Public method responsible for creating a new payment order in the system.
	 * This method returns BAD_REQUEST when token and IdOrder passed as parameter
	 * is invalid. Otherwise, the method returns status CREATED an 
	 * ResponseEntity<OrderPaymentDTO>.
	 * 
	 * @param token,    String format.
	 * @param idOrder,	Long format.
	 * @return Optional<OrderPaymentDTO>
	 * @author Boaz
	 * @since 1.0
	 * 
	 */
	public ResponseEntity<OrderPaymentDTO> getPaymentOrder(String token, Long idOrder) {
		Optional<UserModel> optional = repositoryUser.findByToken(token);
		return repositoryOrder.findById(idOrder).map(resp -> {

			if (optional.isPresent() && resp.getSponsor().getEmail() == optional.get().getEmail()) {
				try {
					objectDTO = new OrderPaymentDTO(
							resp,
							urlPayment + token + "/" + resp.getIdOrder(),
							getQRCodeBase64(token, resp.getIdOrder()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				return ResponseEntity.status(200).body(objectDTO);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token invalido!");
			}
		}).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ordem não existe!");
		});
	}
	
	/**
	 * Public method responsible for get a payment order in the system.
	 * This method returns BAD_REQUEST when IdOrder passed as parameter
	 * is invalid. Otherwise, the method returns status OK an 
	 * ResponseEntity<OrderPaymentDTO>.
	 * 
	 * @param idOrder,	Long format.
	 * @return Optional<OrderPaymentDTO>
	 * @author Boaz
	 * @since 1.0
	 * 
	 */
	public ResponseEntity<OrderPaymentDTO> getPaymentOrderById(Long idOrder) {
		return repositoryOrder.findById(idOrder).map(resp -> {

				try {
					objectDTO = new OrderPaymentDTO(
							resp,
							urlPayment + resp.getSponsor().getToken() + "/" + resp.getIdOrder(),
							getQRCodeBase64(resp.getSponsor().getToken(), resp.getIdOrder()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				return ResponseEntity.status(200).body(objectDTO);

		}).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ordem não existe!");
		});
	}
	
	/**
	 * Public method responsible for deleting an order in the system. The method
	 * returns BAD_REQUEST if token or idOrder does not exist or does not belong
	 * to the same user. Otherwise delete the order and return status OK.
	 * 
	 * @param token
	 * @param idOrder
	 * @return ResponseEntity
	 * @author Boaz
	 * @since 1.0
	 * 
	 */
	public ResponseEntity<Object> deletePaymentOrder(String token, Long idOrder) {
		Optional<UserModel> optional = repositoryUser.findByToken(token);
		return repositoryOrder.findById(idOrder).map(resp -> {

			if (optional.isPresent() && resp.getSponsor().getEmail().equals(optional.get().getEmail()) ) {
				repositoryOrder.deleteById(idOrder);
				
				return ResponseEntity.status(200).build();
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token invalido!");
			}
		}).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ordem não existe!");
		});
	}
	
}
