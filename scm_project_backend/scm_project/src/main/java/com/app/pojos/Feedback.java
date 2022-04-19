package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "feedbacks")
@Data
@NoArgsConstructor
public class Feedback extends BaseEntity{

	@NotBlank(message = "Fill in the input field")
	private String message;
	
	@NotBlank
	private LocalDate date;
	
	@NotBlank
	private String customerName;
	
	
	
	
}
