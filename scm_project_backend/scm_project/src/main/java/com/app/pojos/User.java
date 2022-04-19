package com.app.pojos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User extends BaseEntity {

	@NotEmpty
	@Column(length = 30)
	private String userName;

	@Column(length = 30,unique = true)
	@NotBlank
	private String email;

	@NotBlank
	@Size(max = 300)
	private String password;


	// many to many relationship
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Product> productList;

	public User(String userName, String email, String password) {

		this.userName = userName;
		this.email = email;
		this.password = password;

	}

	
}
