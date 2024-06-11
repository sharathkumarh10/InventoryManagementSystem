package com.jsp.warehousemanagement.entity;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WareHouse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int warehouseId;
	private String name;
	
	@OneToOne
	private Admin admin;

}
