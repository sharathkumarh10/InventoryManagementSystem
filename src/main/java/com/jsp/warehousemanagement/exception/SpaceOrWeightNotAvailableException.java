package com.jsp.warehousemanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SpaceOrWeightNotAvailableException extends RuntimeException {
	
	String message;

}
