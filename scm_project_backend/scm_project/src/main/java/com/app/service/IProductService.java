package com.app.service;

import java.util.List;

import javax.validation.Valid;

import com.app.pojos.Category;
import com.app.pojos.Product;

public interface IProductService {

	List<Product> getAllProducts();

	Product getProductById(long pid);

	void saveProductInfoById(String name, String brand, int stock, String description, double price,
			Category category, String filename, long id);

	String deleteProduct(long id);

	Product save(Product product);

}
