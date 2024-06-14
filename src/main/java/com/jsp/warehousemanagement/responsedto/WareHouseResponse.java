package com.jsp.warehousemanagement.responsedto;

import com.jsp.warehousemanagement.entity.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class WareHouseResponse {
	
	private int warehouseId;
	private String name;
	private double totalCapacity;
	
	private AddressResponse addressResponse;

	
}
