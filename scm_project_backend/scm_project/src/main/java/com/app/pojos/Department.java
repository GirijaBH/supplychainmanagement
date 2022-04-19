package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "departments")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Department extends BaseEntity{
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private EDepartment departmentName;

}
