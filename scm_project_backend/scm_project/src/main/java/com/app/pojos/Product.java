package com.app.pojos;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product extends BaseEntity {

	@Column(name = "product_name")
	@NotEmpty(message ="Product Name can't be blank")
	private String name;

	@NotEmpty(message ="Product brand can't be blank")
	private String brand;

	@NotNull
	@Min(value = 1,message = "Stock cant be less than 1.")
	private int stock;

	@NotNull
	@Column(columnDefinition = "TEXT")
	private String description;

	
	private double price;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;
	
	private String filename;

	//List of reviews for current product.
	@OneToMany(fetch = FetchType.EAGER)
    private List<Feedback> feedbacks;

	public Product(String name, String brand,int stock, String description, double price,Category category, String fileName)
	{
			this.name=name;
			this.brand=brand;
			this.stock=stock;
			this.description=description;
			this.price=price;
			this.category=category;
			this.filename=fileName;
	}
	
}
