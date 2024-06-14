package com.jsp.warehousemanagement.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class ClientRequest {

	@NotNull(message="businessName is not null")
	@NotBlank(message="businessName is not be blank")
	private String businessName;
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "invalid email ")
	private String email;
	
	@Pattern(regexp ="^\\d{10}$", message = "Invalid phone number")
	private String contactNumber; 
}
