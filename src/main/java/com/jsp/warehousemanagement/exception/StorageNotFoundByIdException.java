package com.jsp.warehousemanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StorageNotFoundByIdException extends RuntimeException {
	
	String message;

}
