package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Employee")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee extends BaseEntity {

	@Column(length = 20)
	@NotBlank
	private String firstName;
	
	@Column(length = 20)
	@NotBlank
	private String LastName;
	
	@Column(length = 50)
	@NotBlank
	private String email;
	
	@Column(length = 20)
	private double salary;
	
	private LocalDate joinDate;

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;

	@NotBlank
	private String address;
}
