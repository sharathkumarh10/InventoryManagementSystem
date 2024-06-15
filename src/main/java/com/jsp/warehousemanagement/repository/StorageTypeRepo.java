package com.jsp.warehousemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehousemanagement.entity.StorageType;

public interface StorageTypeRepo extends JpaRepository<StorageType, Integer> {

}
