package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.customexceptionhandler.ResourceNotFoundException;
import com.app.pojos.Category;
import com.app.pojos.Product;
import com.app.repository.IProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements IProductService{

	//dependency injection of productrepository
	@Autowired
	private IProductRepository productRepo;
	
	
	@Override
	public List<Product> getAllProducts() {
		//called api to get all products
		return productRepo.findAll();
	}

	@Override
	public Product getProductById(long id) {
		//find the product using product id
		return productRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Product by ID " + id + " not found!!!!"));
	}


	@Override
	public String deleteProduct(long id) {
		productRepo.deleteById(id);
		return "Product Deleted Successfully!";
	}

	@Override
	public void saveProductInfoById(String name, String brand, int stock, String description, double price,
			Category category, String filename, long id) {
		productRepo.saveProductInfoById(name, brand, stock, description, price, category, filename, id);
		}

	@Override
	public Product save(Product product) {

		return productRepo.save(product);
	}

}
