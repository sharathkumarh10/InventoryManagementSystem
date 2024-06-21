package com.jsp.warehousemanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class InventoriesNotFoundException extends RuntimeException {
	
	String message;

}