package com.jsp.warehousemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehousemanagement.entity.Inventory;

public interface InventoryRepo extends JpaRepository<Inventory, Integer> {

}
