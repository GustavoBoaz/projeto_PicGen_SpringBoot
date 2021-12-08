package com.picgen.App.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.picgen.App.utils.StatusPayment;

/**
 * Order base abstraction.abstraction.This class is a class that will represent
 * a table in the system. The columns is:
 * 
 * <p>
 * - idOrder: Represents order id column. This column is automatically generated
 * and AUTO_INCREMET;
 * </p>
 * <p>
 * - requestDate: Represents request date column. String format "yyyy-MM-dd".
 * This column is automatically generated;
 * </p>
 * <p>
 * - deadlineDate: Represents deadline date column. String format "yyyy-MM-dd".
 * This column is automatically generated;
 * </p>
 * <p>
 * - statusPay: Represents pay status column. ENUM StatusPayment format
 * {PENDING, CANCELED, PAID}. This column is automatically generated witch value
 * PENDING;
 * </p>
 * <p>
 * - value: Represents value column. Float NOT NULL;
 * </p>
 * <p>
 * - emailBuyer: Represents buyer email column. String NOT NULL format
 * {email@domain.com};
 * </p>
 * <p>
 * - documentBuyer: Represents buyer document column. String NOT NULL format
 * {XXX.XXX.XXX-XX};
 * </p>
 * 
 * @author Bart Neto
 * @author Mariana
 * @since 1.1
 * @see UserModel
 *
 */
@Entity
@Table(name = "tb_orders")
public class OrderModel {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idOrder;
	private @JsonFormat(pattern = "yyyy-MM-dd") LocalDate requestDate = LocalDate.now();
	private @JsonFormat(pattern = "yyyy-MM-dd") LocalDate deadlineDate = requestDate.plusDays(1);
	private @Enumerated(EnumType.STRING) StatusPayment statusPayment = StatusPayment.PENDENTE;

	private @NotNull Float value;
	private @NotBlank @Email String emailBuyer;
	private @NotBlank @CPF String documentBuyer;

	@ManyToOne
	@JoinColumn(name = "fk_sponsor")
	@JsonIgnoreProperties({ "myOrders", "idUser", "password", "token", "wallet" })
	private UserModel sponsor;
	
	public OrderModel() {
		super();
	}

	public OrderModel(Float value, String emailBuyer, String documentBuyer, UserModel sponsor) {
		super();
		this.value = value;
		this.emailBuyer = emailBuyer;
		this.documentBuyer = documentBuyer;
		this.sponsor = sponsor;
	}

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

	public StatusPayment getStatusPayment() {
		return statusPayment;
	}

	public void setStatusPayment(StatusPayment statusPayment) {
		this.statusPayment = statusPayment;
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

	public UserModel getSponsor() {
		return sponsor;
	}

	public void setSponsor(UserModel sponsor) {
		this.sponsor = sponsor;
	}

}
