package com.jsp.warehousemanagement.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class WareHouseRequest {
	@NotNull(message = "Warehousename cannot be null")
	@NotBlank(message = "Warehousename cannot be blank")
	private String name;
	
	private int totalCapacity;

}
