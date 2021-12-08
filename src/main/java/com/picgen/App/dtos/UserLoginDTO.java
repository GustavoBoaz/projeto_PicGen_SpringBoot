package com.picgen.App.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Mirror class for data entry. Your representation must be used to request
 * credentials from the administrator user;
 * 
 * Fields:
 * 
 * <p>
 * - email: String type email;
 * </p>
 * <p>
 * - password: String in between 4 and 15 characteres;
 * </p>
 * 
 * @author JULIA SOBRAL
 * @author BOAZ
 * @since 1.0
 * @see UserCredentialsDTO
 * @see UserRegistrationDTO
 * @see UserUpdateDTO
 *
 */
public class UserLoginDTO {

	private @NotBlank(message = "Inserir 'email' valido") @Email String email;
	private @NotBlank(message = "Inserir 'password' de 4 á 15 caracteres") @Size(min = 3, max = 15) String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
