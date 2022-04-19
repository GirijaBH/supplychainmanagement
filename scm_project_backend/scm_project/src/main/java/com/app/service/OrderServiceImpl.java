package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.pojos.Order;
import com.app.pojos.User;
import com.app.repository.IOrderRepository;
import com.app.repository.IUserRepository;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService{

	@Autowired
	private IOrderRepository orderRepository;
	
	@Autowired
	private IUserRepository userRepo;
	
	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}

	@Override
	public Order saveOrder(Order order) {
		// TODO Auto-generated method stub
		return orderRepository.save(order);
	}



	@Override
	public List<Order> findOrderByUser(User user) {
		
		 return orderRepository.findOrderByUser(user);
	}

	@Override
	public Order postOrder(Order validOrder, User userSession) {
		// TODO Auto-generated method stub
		User user = userRepo.findByEmail(userSession.getEmail());
		Order newOrder = new Order(user);
		
		newOrder.getProductList().addAll(user.getProductList());
		newOrder.setTotalPrice(validOrder.getTotalPrice());
		newOrder.setAddress(validOrder.getAddress());
		newOrder.setQuantity(validOrder.getQuantity());
		user.getProductList().clear();
		orderRepository.save(newOrder);
		
		return newOrder;
	}

	

}
