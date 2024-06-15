package com.jsp.warehousemanagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.warehousemanagement.requestdto.StorageTypeRequest;
import com.jsp.warehousemanagement.responsedto.StorageTypeResponse;
import com.jsp.warehousemanagement.utility.ResponseStructure;

public interface StorageTypeService {

	ResponseEntity<ResponseStructure<StorageTypeResponse>> createStorageType(StorageTypeRequest storageTypeRequest);

	ResponseEntity<ResponseStructure<StorageTypeResponse>> updateStorageType(StorageTypeRequest storageTypeRequest,
			int storageTypeId);

	ResponseEntity<ResponseStructure<List<StorageTypeResponse>>> findAllStorageType();

}
