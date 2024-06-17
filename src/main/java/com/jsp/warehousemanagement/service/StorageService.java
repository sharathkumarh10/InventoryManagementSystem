package com.jsp.warehousemanagement.service;

import org.springframework.http.ResponseEntity;


import com.jsp.warehousemanagement.requestdto.StorageRequest;
import com.jsp.warehousemanagement.responsedto.StorageResponse;
import com.jsp.warehousemanagement.utility.ResponseStructure;
import com.jsp.warehousemanagement.utility.SimpleStructure;

import jakarta.validation.Valid;

public interface StorageService {

	ResponseEntity<SimpleStructure<String>> createStorage(@Valid StorageRequest storageRequest, int warehouseId,
			int noOfStorageUnits,int storagetypeId);

	ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(int storageId, StorageRequest storageRequest);

//	ResponseEntity<ResponseStructure<StorageResponse>> findByCapacityAndLengthAndBreadthAndHeight(double capacityInkg,
//			double lengthInMetres, double breadthInMetres, double heightInMetres);

}
