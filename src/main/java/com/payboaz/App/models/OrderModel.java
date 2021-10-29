package com.payboaz.App.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.payboaz.App.utils.PayStatus;

/**
 * Classe Model que representa uma entidade no banco de dados com os campos
 * abaixo:
 * 
 * <p>
 * - idOrder: Utilizado como chave primaria da tabela
 * </p>
 * <p>
 * - requestDate: Coluna data da requisição
 * </p>
 * <p>
 * - deadlineDate: Coluna data limite para finalizar pagamento
 * </p>
 * <p>
 * - statusPay: Coluna para status do pagamento PENDENTE PAGO CANCELADO
 * </p>
 * <p>
 * - value: Coluna valor para pagamento
 * </p>
 * <p>
 * - emailBuyer: Coluna email do pagador
 * </p>
 * <p>
 * - documentBuyer: Coluna documeto CPF do pagador
 * </p>
 * 
 * @author Bart Neto
 * @since 1.0
 *
 */
@Entity
@Table(name = "tb_orders")
public class OrderModel {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idOrder;
	private @JsonFormat(pattern = "yyyy-MM-dd") LocalDate requestDate = LocalDate.now();
	private @JsonFormat(pattern = "yyyy-MM-dd") LocalDate deadlineDate = requestDate.plusDays(1);
	private @Enumerated(EnumType.STRING) PayStatus statusPay = PayStatus.PENDENTE;

	private Float value;
	private String emailBuyer;
	private String documentBuyer;

	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}

	public LocalDate getDeadlineDate() {
		return deadlineDate;
	}

	public void setDeadlineDate(LocalDate deadlineDate) {
		this.deadlineDate = deadlineDate;
	}

	public PayStatus getStatusPay() {
		return statusPay;
	}

	public void setStatusPay(PayStatus statusPay) {
		this.statusPay = statusPay;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public String getEmailBuyer() {
		return emailBuyer;
	}

	public void setEmailBuyer(String emailBuyer) {
		this.emailBuyer = emailBuyer;
	}

	public String getDocumentBuyer() {
		return documentBuyer;
	}

	public void setDocumentBuyer(String documentBuyer) {
		this.documentBuyer = documentBuyer;
	}

}
