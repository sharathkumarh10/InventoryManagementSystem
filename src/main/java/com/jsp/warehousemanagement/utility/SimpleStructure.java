package com.jsp.warehousemanagement.utility;

public class SimpleStructure<T> {
	
	int status;
	String message;
	public int getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
	public SimpleStructure<T> setStatus(int status) {
		this.status = status;
		return this;
	}
	public SimpleStructure<T> setMesssage(String message) {
		this.message = message;
		return this;
	}

}
