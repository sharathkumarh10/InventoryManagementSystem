package com.jsp.warehousemanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AdminNotFoundByIdException extends RuntimeException {
	
	private String message;

}
