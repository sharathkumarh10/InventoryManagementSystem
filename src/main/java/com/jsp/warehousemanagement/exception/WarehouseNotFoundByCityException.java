package com.jsp.warehousemanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WarehouseNotFoundByCityException extends RuntimeException {
	
	private String message;

}
