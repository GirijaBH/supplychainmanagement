package com.app.pojos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order extends BaseEntity{

	@NotBlank
	private int quantity;
	
	@NotBlank
	private LocalDate date;
	
	@NotBlank
	private double totalPrice;
	
	@OrderColumn
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Product> productList = new ArrayList<Product>();
	
	@NotBlank(message = "Fill in the input field")
    private String address;
	
	@Column(updatable=false, insertable=false)
	LocalDate added_date;
	
	@ManyToOne
    private User user;
	
	public Order(User user)
	{
		this.user=user;
		this.added_date=LocalDate.now();
	}
}
