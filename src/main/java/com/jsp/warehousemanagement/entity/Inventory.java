package com.jsp.warehousemanagement.entity;

import java.time.LocalDate;
import java.util.List;

import com.jsp.warehousemanagement.enums.MaterialType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	private String productTitle;
	private double lengthInMetres;
	private double breadthInMetres;
	private double heightInMetres;
	private double weightInKg;

	@Enumerated(EnumType.STRING)
	List<MaterialType>materialType;
	private LocalDate restockedAt;
	private int sellerId;
	

//	
	@ManyToOne
	
	private Client client;
	
	@OneToMany(mappedBy = "inventory")
	private List<Stock> stock;
	
	@ManyToMany(mappedBy = "inventories")
	List<PurchaseOrder> purchaseOrders;

}
