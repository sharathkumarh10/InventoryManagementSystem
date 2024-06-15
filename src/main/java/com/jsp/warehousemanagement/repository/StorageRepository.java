package com.jsp.warehousemanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.jsp.warehousemanagement.entity.Storage;
import com.jsp.warehousemanagement.responsedto.StorageResponse;
import com.jsp.warehousemanagement.utility.ResponseStructure;



public interface StorageRepository extends JpaRepository<Storage, Integer> {

//	Optional<Storage> findByCapacityInkgAndLengthInMetresAndBreadthInMetresAndHeightInMetres(double capacityInkg,
//			double lengthInMetres, double breadthInMetres, double heightInMetres);
	
	

}
