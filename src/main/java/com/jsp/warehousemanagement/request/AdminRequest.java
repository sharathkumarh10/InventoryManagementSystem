package com.jsp.warehousemanagement.request;

import java.util.List;

import com.jsp.warehousemanagement.enums.AdminType;
import com.jsp.warehousemanagement.enums.Privilege;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class AdminRequest {
	@NotNull(message = "Username cannot be null")
	@NotBlank(message = "Username cannot be blank")
	private String name;
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "invalid email ")
	private String email;
	 @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
             message = "Password must be alpha-numeric, contain at least 1 capital letter, lowercase letter, special character, and numeric character. It must be at least 8 characters in length.")
	private String password;

	

}
