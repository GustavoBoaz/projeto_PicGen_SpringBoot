package com.payboaz.App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payboaz.App.models.OrderModel;

/**
 * Inteface reponseble for inheriting crud methods
 * 
 * @author JULIA SOBRAL
 * @since 1.0
 * @see UserRepository
 *
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {

}
