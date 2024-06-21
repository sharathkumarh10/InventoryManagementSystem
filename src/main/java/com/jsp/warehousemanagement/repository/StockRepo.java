package com.jsp.warehousemanagement.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehousemanagement.entity.Inventory;
import com.jsp.warehousemanagement.entity.Stock;
import com.jsp.warehousemanagement.entity.Storage;

public interface StockRepo extends JpaRepository<Stock, Integer>{

	

	Optional<Stock> findByInventory_ProductId(int productId);

	List<Stock> findByStorageAndInventory(Storage storage, Inventory inventory);



}
