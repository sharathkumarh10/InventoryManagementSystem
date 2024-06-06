package com.jsp.warehousemanagement.entity;

import java.util.List;

import com.jsp.warehousemanagement.enums.AdminType;
import com.jsp.warehousemanagement.enums.Privilege;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	private String name;
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private AdminType adminType;
	
	

}
