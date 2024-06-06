package com.jsp.warehousemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehousemanagement.entity.WareHouse;

public interface WareHouseRepository extends JpaRepository<WareHouse, Integer> {

}
