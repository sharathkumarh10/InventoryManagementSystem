package com.jsp.warehousemanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehousemanagement.entity.Admin;
import com.jsp.warehousemanagement.enums.AdminType;


public interface AdminRepository extends JpaRepository<Admin,Integer > {
	
	public boolean existsByAdminType(AdminType adminType);

	public Optional<Admin> findByEmail(String username);
	
	

}
