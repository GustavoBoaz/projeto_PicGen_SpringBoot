package com.picgen.App.repositories;

import java.util.List;

import com.picgen.App.models.OrderModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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

	/**
	 * Method used to search by emailBuyer
	 * 
	 * @author Arthur
	 * @param emailBuyer
	 * @return List<OrderModel>
	 * @since 1.0
	 * 
	 */
	public List<OrderModel> findAllByEmailBuyerContaining(String emailBuyer);

}
