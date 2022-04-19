package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Order;
import com.app.pojos.User;


public interface IOrderRepository extends JpaRepository<Order, Long> {

	List<Order> findOrderByUser(User user);
	
}
