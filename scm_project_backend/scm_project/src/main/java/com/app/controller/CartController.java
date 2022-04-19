package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Product;
import com.app.pojos.User;
import com.app.service.IUserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("scm/api")
public class CartController {
	
	@Autowired
	private IUserService userService;
	
	
	@GetMapping("/cart/{email}")
	public ResponseEntity<?> getCart(@PathVariable String email)
	{
		User user = userService.getUserByEmail(email);
		List<Product> productList = user.getProductList();
		
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}
	
	@PostMapping("/cart/add")
    public ResponseEntity<?> addToCart(@RequestBody Product perfume, @AuthenticationPrincipal User userSession) {
        User user = userService.getUserByEmail(userSession.getEmail());
        user.getProductList().add(perfume);

        userService.saveUser(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
	
	
    @PostMapping("/cart/remove")
    public ResponseEntity<?> removeFromCart(@RequestBody Product perfume, @AuthenticationPrincipal User userSession) {
        User user = userService.getUserByEmail(userSession.getEmail());
        user.getProductList().remove(perfume);

        userService.saveUser(user);

        List<Product> perfumeList = user.getProductList();

        return new ResponseEntity<>(perfumeList, HttpStatus.OK);
    }
	
	
}
