package com.jsp.warehousemanagement.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehousemanagement.entity.WareHouse;

public interface WareHouseRepository extends JpaRepository<WareHouse, Integer> {
		public Optional<WareHouse> findById(int warehouseId);
}
