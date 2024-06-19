package com.jsp.warehousemanagement.requestdto;

import java.time.LocalDate;
import java.util.List;

import com.jsp.warehousemanagement.enums.MaterialType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class InventoryRequest {
	
	private String productTitle;
	private double lengthInMetres;
	private double breadthInMetres;
	private double heightInMetres;
	private double weightInKg;
	private int quantity;
	
	List<MaterialType>materialType;
	
	private int sellerId;

}
