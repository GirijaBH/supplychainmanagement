package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Product;
import com.app.service.IProductService;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/scm/api/home")
public class HomeController {

	
	@Autowired
	private IProductService productService;
	
	@GetMapping
	public ResponseEntity<?> getAllProducts()
	{
		List<Product> productList = productService.getAllProducts();
		
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}
	
	@GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") int productId) {
		Product product = productService.getProductById(productId);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }
	
	
}
