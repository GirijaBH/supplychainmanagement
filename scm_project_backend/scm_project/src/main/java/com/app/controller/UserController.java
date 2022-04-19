package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AuthenticationRequestDTO;
import com.app.pojos.Order;
import com.app.pojos.User;
import com.app.service.IOrderService;
import com.app.service.IUserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/scm/api")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IOrderService orderService;
	
	@GetMapping("/user/edit")
	public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal User userSession)
	{
		User user = userService.getUserByEmail(userSession.getEmail());
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PutMapping("/user/edit")
    public ResponseEntity<?> updateUserInfo(
            @AuthenticationPrincipal User userSession,
            @RequestBody AuthenticationRequestDTO request
    ) {
        userService.updateProfile(userSession, request.getPassword(), request.getEmail());

        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@GetMapping("/user/orders")
	public ResponseEntity<?> getAllUserOrders(@AuthenticationPrincipal User userSession)
	{
		User user = userService.getUserByEmail(userSession.getEmail());
		List<Order> orderList = orderService.findOrderByUser(user);
		return new ResponseEntity<>(orderList, HttpStatus.OK);
	}
}
