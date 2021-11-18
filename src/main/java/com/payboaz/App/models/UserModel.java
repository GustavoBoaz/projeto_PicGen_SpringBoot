package com.payboaz.App.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Admin User account based abstraction.This class is a class that will
 * represent a table in the system. The columns is:
 * 
 * <p>
 * - idUser: represents user id column. This column is automatically generated
 * and AUTO_INCREMET;
 * </p>
 * <p>
 * - name: represents user name column. String NOT NULL;
 * </p>
 * <p>
 * - email: represents user email column. String NOT NULL;
 * </p>
 * <p>
 * - password: represents user password column. String NOT NULL;
 * </p>
 * <p>
 * - token: represents user token column. String NOT NULL;
 * </p>
 * <p>
 * - wallet: represents user wallet column Float NULL;
 * </p>
 * 
 * @author Boaz
 * @author Mariana
 * @since 1.1
 * @see OrderModel
 * 
 */
@Entity
@Table(name = "tb_users")
public class UserModel {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idUser;
	private @NotBlank String name;
	private @NotBlank @Email String email;
	private @NotBlank String password;
	private @NotBlank String token;
	private @SuppressWarnings("deprecation") Float wallet = new Float(0.00);

	@OneToMany(mappedBy = "sponsor", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({ "sponsor" })
	private List<OrderModel> myOrders = new ArrayList<>();

	public UserModel() {
		
	}
	
	public UserModel(String name, String email, String password, String token) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.token = token;
	}

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

	public Float getWallet() {
		return wallet;
	}

	public void setWallet(Float wallet) {
		this.wallet = wallet;
	}

	public List<OrderModel> getMyOrders() {
		return myOrders;
	}

	public void setMyOrders(List<OrderModel> myOrders) {
		this.myOrders = myOrders;
	}

}
