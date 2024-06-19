package com.jsp.warehousemanagement.responsedto;

import java.time.LocalDate;
import java.util.List;

import com.jsp.warehousemanagement.enums.MaterialType;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@Builder
public class InventoryResponse {
	private int productId;
	private String productTitle;
	private double lengthInMetres;
	private double breadthInMetres;
	private double heightInMetres;
	private double weightInKg;
	private int quantity;
	
	List<MaterialType>materialType;
	private LocalDate restockedAt;
	private int sellerId;

}
