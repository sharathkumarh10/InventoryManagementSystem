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
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int clientId;
	private String businessName;
	private String email;
	private String contactNumber; 
	
	private String apiKey;
	private String message;
}
