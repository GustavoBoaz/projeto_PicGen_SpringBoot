package com.payboaz.App.dtos;

import com.payboaz.App.models.OrderModel;

/**
 * Mirror class for outputting data. Its representation should be used to return
 * request data and payment url with qrcode.
 * 
 * Fields:
 * 
 * <p>
 * - dataOrder: Object OrderModel;
 * </p>
 * <p>
 * - urlPayment: String url for payment;
 * </p>
 * <p>
 * - qrCode: String Base64 qrcode;
 * </p>
 * 
 * @author BOAZ
 * @since 1.0
 * @see OrderRegistrationDTO
 *
 */
public class OrderPaymentDTO {

	private OrderModel dataOrder;
	private String urlPayment;
	private String qrCode;

	public OrderPaymentDTO(OrderModel dataOrder, String urlPayment, String qrCode) {
		super();
		this.dataOrder = dataOrder;
		this.urlPayment = urlPayment;
		this.qrCode = qrCode;
	}

	public OrderModel getDataOrder() {
		return dataOrder;
	}

	public void setDataOrder(OrderModel dataOrder) {
		this.dataOrder = dataOrder;
	}

	public String getUrlPayment() {
		return urlPayment;
	}

	public void setUrlPayment(String urlPayment) {
		this.urlPayment = urlPayment;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
}
