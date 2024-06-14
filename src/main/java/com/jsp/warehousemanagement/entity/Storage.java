package com.jsp.warehousemanagement.entity;

import java.util.List;

import com.jsp.warehousemanagement.enums.MaterialType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Storage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int storageId;
	private String blockName;
	private String section;
	private double lengthInMetres;
	private double breadthInMetres;
	private double heightInMetres;
	private double capacityInkg;
	private double maxAdditionalWeightInKg;
	private double availableAreaInMetre;
	@Enumerated(EnumType.STRING)
	private List<MaterialType>materialType;
	
	@ManyToOne
	private WareHouse wareHouse;
	
	
}
