package com.app.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.customexceptionhandler.ControllerUtils;
import com.app.pojos.Order;
import com.app.pojos.Product;
import com.app.pojos.User;
import com.app.service.IOrderService;
import com.app.service.IUserService;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/scm/api/order")
public class OrderController {

	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/orders")
	public ResponseEntity<?> getOrder(@AuthenticationPrincipal User userSession)
	{
		User user = userService.getUserByEmail(userSession.getEmail());
		List<Product> productList = user.getProductList();
		
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}
	
	@PostMapping("/order")
	public ResponseEntity<?> postOrder(@AuthenticationPrincipal User userSession,
			@Valid @RequestBody Order validOrder,
			BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
			return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
		}
		else {
            Order order = orderService.postOrder(validOrder, userSession);

            return new ResponseEntity<>(order, HttpStatus.CREATED);
        }
	}
	
	@GetMapping("/order/finalize")
    public ResponseEntity<?> finalizeOrder() {
        List<Order> orderList = orderService.findAll();
        Order orderIndex = orderList.get(orderList.size() - 1);

        return new ResponseEntity<>(orderIndex.getId(), HttpStatus.OK);
    }
	
	@GetMapping("/order/list")
    public ResponseEntity<?> getUserOrdersList(@AuthenticationPrincipal User userSession) {
        User user = userService.getUserByEmail(userSession.getEmail());
        List<Order> orders = orderService.findOrderByUser(user);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
