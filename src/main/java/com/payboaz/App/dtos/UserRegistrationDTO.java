package com.payboaz.App.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Mirror class for data entry. Your representation must be used to register a
 * new administrator user to the system.
 * 
 * Fields:
 * 
 * <p>
 * - name: String in between 4 and 50 characteres;
 * </p>
 * <p>
 * - email: String type email;
 * </p>
 * <p>
 * - password: String in between 4 and 100 characteres;
 * </p>
 * 
 * @author JULIA SOBRAL
 * @author BOAZ
 * @since 1.0
 * @see UserCredentialsDTO
 * @see UserLoginDTO
 * @see UserUpdateDTO
 *
 */
public class UserRegistrationDTO {

	private @NotBlank(message = "Inserir 'name' de 4 á 50 caracteres") @Size(min = 3, max = 50) String name;
	private @NotBlank(message = "Inserir 'email' valido") @Email String email;
	private @NotBlank(message = "Inserir 'password' de 4 á 15 caracteres") @Size(min = 3, max = 15) String password;

	public UserRegistrationDTO() {
		
	}
	
	public UserRegistrationDTO(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
