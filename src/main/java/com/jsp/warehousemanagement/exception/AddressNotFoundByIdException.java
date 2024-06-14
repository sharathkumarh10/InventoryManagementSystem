package com.jsp.warehousemanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddressNotFoundByIdException extends RuntimeException {
	
	String message;

}
