package com.payboaz.App.dtos;

/**
 * 
 * @author JULIA SOBRAL
 * @since 1.0
 *
 */
public class UserCredentialsDTO {

	private Long idUser;
	private String name;
	private String email;
	private String password;
	private String token;
	private String authorizationBasic;

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAuthorizationBasic() {
		return authorizationBasic;
	}

	public void setAuthorizationBasic(String authorizationBasic) {
		this.authorizationBasic = authorizationBasic;
	}

}
