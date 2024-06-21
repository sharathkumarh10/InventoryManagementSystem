package com.jsp.warehousemanagement.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
@Entity
@Setter
@Getter
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stockId;
	private int quantity;
	
	@ManyToOne
	private Inventory inventory;
	
	@ManyToOne
	private Storage storage;

}
