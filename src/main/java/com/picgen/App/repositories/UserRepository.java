package com.picgen.App.repositories;

import java.util.Optional;

import com.picgen.App.models.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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

}
