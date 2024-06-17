package com.jsp.warehousemanagement.requestdto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
public class StorageTypeRequest {
	
	
	private double lengthInMetres;
	private double breadthInMetres;
	private double heightInMetres;
	private double capacityInkg;

}
