package com.jsp.warehousemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehousemanagement.requestdto.StorageRequest;
import com.jsp.warehousemanagement.responsedto.StorageResponse;
import com.jsp.warehousemanagement.service.StorageService;
import com.jsp.warehousemanagement.utility.ResponseStructure;
import com.jsp.warehousemanagement.utility.SimpleStructure;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/v2")
public class StorageController {
	
	@Autowired
	private StorageService storageService;
	@PreAuthorize("hasAuthority('READ')")
	@PostMapping("/warehouses/{warehouseId}/storages")
	public ResponseEntity<SimpleStructure<String>>createStorage(@RequestBody @Valid StorageRequest storageRequest,
			@PathVariable int warehouseId ,@RequestParam ("no_of_storage_units") int noOfStorageUnits){
		return storageService.createStorage(storageRequest,warehouseId,noOfStorageUnits);
	}
	
	@PreAuthorize("hasAuthority('UPDATE_STORAGE')")
	@PutMapping("/storages/{storageId}")
	public ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(@PathVariable int storageId, @RequestBody StorageRequest storageRequest) {
	
		return storageService.updateStorage(storageId, storageRequest);
	}

}
