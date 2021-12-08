package com.picgen.App.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

/**
 * Mirror class for data entry. Your representation must be used to register a
 * new payment order.
 * 
 * Fields:
 * 
 * <p>
 * - value: Represents Float value to be paid;
 * </p>
 * <p>
 * - emailBuyer: Represents String buyer email {email@domain.com};
 * </p>
 * <p>
 * - documentBuyer: Represents String buyer document {XXX.XXX.XXX-XX};
 * </p>
 * 
 * @author Boaz
 * @since 1.0
 * @see OrderPaymentDTO
 *
 */
public class OrderRegistrationDTO {

	private @NotNull(message = "Inserir 'valor' valido") Float value;
	private @NotBlank(message = "Inserir 'emailComprador' valido") @Email String emailBuyer;
	private @NotBlank(message = "Inserir 'cpfComprador' valido") @CPF String documentBuyer;

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