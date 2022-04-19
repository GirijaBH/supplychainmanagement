package com.app.dto;

import com.app.pojos.Category;

import lombok.Data;

@Data
public class ProductDTO {

	
	private String name;

	private String brand;
	
	private int stock;

	private String description;

	private double price;
	
	private Category category;
	
}
