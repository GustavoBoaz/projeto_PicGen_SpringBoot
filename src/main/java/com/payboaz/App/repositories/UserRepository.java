package com.payboaz.App.repositories;

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

}
