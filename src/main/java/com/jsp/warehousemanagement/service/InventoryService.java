package com.jsp.warehousemanagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.warehousemanagement.requestdto.InventoryRequest;
import com.jsp.warehousemanagement.responsedto.InventoryResponse;

import com.jsp.warehousemanagement.utility.ResponseStructure;

public interface InventoryService {

//	ResponseEntity<ResponseStructure<InventoryResponse>> createInventory(InventoryRequest inventoryRequest, int storageId);

	ResponseEntity<ResponseStructure<InventoryResponse>> findInventory(int productId);

	ResponseEntity<ResponseStructure<List<InventoryResponse>>> findAllInventories();

	ResponseEntity<ResponseStructure<InventoryResponse>> createInventory(InventoryRequest inventoryRequest,int clientId, int storageId);

}
