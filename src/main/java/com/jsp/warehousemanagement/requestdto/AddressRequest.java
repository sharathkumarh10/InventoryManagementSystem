package com.jsp.warehousemanagement.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class AddressRequest {
	
	@NotNull(message = "AddressLine cannot be null")
	@NotBlank(message = "AddressLine cannot be blank")
	private String addressLine;
	@NotNull(message = "City cannot be null")
	@NotBlank(message = "City cannot be blank")
	private String city;
	@NotNull(message = "State cannot be null")
	@NotBlank(message = "State cannot be blank")
	private String state;
	@NotNull(message = "Country cannot be null")
	@NotBlank(message = "Country cannot be blank")
	private String country;
	
	private int pincode;
	private String longitude;
	private String latitude;

}
