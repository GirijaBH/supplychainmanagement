package com.app.service;

import java.util.List;

import com.app.pojos.Order;
import com.app.pojos.User;

public interface IOrderService {

	List<Order> findAll();
	
	Order saveOrder(Order order);

	List<Order> findOrderByUser(User user);
	
	Order postOrder(Order validOrder, User userSession);
	
}
