package com.payboaz.App.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payboaz.App.models.UserModel;

/**
 * Inteface reponseble for inheriting crud methods
 * 
 * @author JULIA SOBRAL
 * @since 1.0
 * @see OrderRepository
 *
 */
@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

	/**
	 * Method used to search by token
	 * 
	 * @param token
	 * @return Optional<UserModel>
	 * @author JULIA SOBRAL
	 * @since 1.0
	 * 
	 */
	Optional<UserModel> findByToken(String token);

	/**
	 * Method used to search by email
	 * 
	 * @param email
	 * @return Optional<UserModel>
	 * @author JULIA SOBRAL
	 * @since 1.0
	 * 
	 */
	Optional<UserModel> findByEmail(String email);
	
	/**
	 * Method used to search by name
	 * 
	 * @param name
	 * @return Optional<UserModel>
	 * @author BOAZ
	 * @since 1.0
	 * 
	 */
	List<UserModel> findAllByNameContainingIgnoreCase(String name);

}
