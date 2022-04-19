package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.Category;
import com.app.pojos.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByCategory(Category categoryName);

	List<Product> findByBrand(String brandName);

	List<Product> findByPriceBetweenOrderByPriceDesc(double startingPrice, double endingPrice);

	@Modifying
	@Query("update Product p set p.name = ?1, p.brand = ?2, p.stock = ?3, p.description = ?4, "
			+ "p.price = ?5, p.category = ?6, p.filename = ?7 where p.id = ?8")
	void saveProductInfoById(String name, String brand, int stock, String description, double price, Category category,
			String filename, Long id);
}
