package com.jsp.warehousemanagement.responsedto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class StockResponse {
	
	private int stockId;
	private int quantity;
	private StorageResponse storage;

}
