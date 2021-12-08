package com.picgen.App.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Mirror class for data entry. Your representation must be used to update a
 * current administrator user to the system. A valid id and token must be
 * passed.
 * 
 * Fields:
 * 
 * <p>
 * - idUser: Long for validation;
 * </p>
 * <p>
 * - token: String for validation;
 * </p>
 * <p>
 * - name: String in between 4 and 50 characteres for update;
 * </p>
 * <p>
 * - email: String type email for update;
 * </p>
 * <p>
 * - password: String in between 4 and 100 characteres for update;
 * </p>
 * 
 * @author JULIA SOBRAL
 * @author BOAZ
 * @since 1.0
 * @see UserCredentialsDTO
 * @see UserLoginDTO
 * @see UserRegistrationDTO
 *
 */
public class UserUpdateDTO {

	private @NotNull Long idUser;
	private @NotBlank String token;
	private @NotBlank(message = "Inserir 'name' de 4 á 50 caracteres") @Size(min = 3, max = 50) String name;
	private @NotBlank(message = "Inserir 'email' valido") @Email String email;
	private @NotBlank(message = "Inserir 'password' de 4 á 100 caracteres") @Size(min = 3, max = 100) String password;

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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
