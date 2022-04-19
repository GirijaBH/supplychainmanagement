package com.app.requestresponse;

import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignUpRequest {

	@NotBlank
	@Size(max = 20)
	private String username;

	@Column(unique = true)
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	private Set<String> role;

	@NotBlank
	@Size(min =8 , max = 20)
	private String password;
		
	


}
