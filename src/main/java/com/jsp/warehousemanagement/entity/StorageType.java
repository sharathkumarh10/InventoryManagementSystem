package com.jsp.warehousemanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@Entity
@Setter
@Getter
public class StorageType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int storageTypeId;
	private double lengthInMetres;
	private double breadthInMetres;
	private double heightInMetres;
	private double capacityInkg;
	private int unitsAvailable;

}
