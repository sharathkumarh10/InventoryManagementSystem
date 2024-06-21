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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehousemanagement.requestdto.InventoryRequest;
import com.jsp.warehousemanagement.responsedto.InventoryResponse;
import com.jsp.warehousemanagement.responsedto.StockResponse;
import com.jsp.warehousemanagement.service.InventoryService;
import com.jsp.warehousemanagement.utility.ResponseStructure;
@RestController
@RequestMapping("/api/v2")
public class InventoryController {
	@Autowired
	private InventoryService inventoryService;
	
	@PostMapping("/clients/{clientId}/storages/{storageId}/inventories")
	public ResponseEntity<ResponseStructure<InventoryResponse>> createInventory(@RequestBody InventoryRequest inventoryRequest
			,@PathVariable int clientId, @PathVariable int storageId,@RequestParam("quantity")int quantity){
		return inventoryService.createInventory(inventoryRequest,clientId,storageId,quantity);
		
	}
	

	@GetMapping("/clients/{productId}/inventories")
	public ResponseEntity<ResponseStructure<InventoryResponse>> findInventory(@PathVariable int productId ) {
		return inventoryService.findInventory(productId);
	}
	

	@GetMapping("/clients/inventories")
	public ResponseEntity<ResponseStructure<List<InventoryResponse>>> findAllInventories() {
		return inventoryService.findAllInventories();
	}
	@PutMapping("/clients/{productId}/inventories")
	public ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(@RequestBody InventoryRequest inventoryRequest,@PathVariable int productId){
		return inventoryService.updateInventory(inventoryRequest,productId);
	}
	@PutMapping("/clients/products/{productId}/storages/{storageId}")
	public ResponseEntity<ResponseStructure<List<StockResponse>>> updateInventoryByQuantity(@PathVariable int productId
			,@PathVariable int storageId,@RequestParam("quantity") int quantity) {
		return inventoryService.updateInventoryByQuantity(productId, storageId, quantity);
	}

}
