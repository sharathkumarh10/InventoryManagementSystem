package com.jsp.warehousemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehousemanagement.requestdto.StorageTypeRequest;
import com.jsp.warehousemanagement.responsedto.StorageTypeResponse;
import com.jsp.warehousemanagement.service.StorageTypeService;
import com.jsp.warehousemanagement.utility.ResponseStructure;
@RestController
@RequestMapping("/api/v2")
public class StorageTypeController {
	
	@Autowired
	private StorageTypeService storageTypeService;
	@PostMapping("/storageType")
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> createStorageType(@RequestBody StorageTypeRequest storageTypeRequest){
		return storageTypeService.createStorageType(storageTypeRequest);
	}
	
	@PutMapping("/storageType/{storageTypeId}")
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> updateStorageType(@RequestBody StorageTypeRequest storageTypeRequest,@PathVariable int storageTypeId){
		return storageTypeService.updateStorageType(storageTypeRequest,storageTypeId);
	}
	@GetMapping("/storageTypes")
	public ResponseEntity<ResponseStructure<List<StorageTypeResponse>>> findAllStorageType(){
		return storageTypeService.findAllStorageType();
	}
	
	

}
