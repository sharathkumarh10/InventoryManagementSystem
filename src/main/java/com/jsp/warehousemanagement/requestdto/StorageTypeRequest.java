package com.jsp.warehousemanagement.requestdto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StorageTypeRequest {
	
	
	private double lengthInMetres;
	private double breadthInMetres;
	private double heightInMetres;
	private double capacityInkg;

}
