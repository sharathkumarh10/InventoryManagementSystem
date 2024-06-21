package com.jsp.warehousemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehousemanagement.requestdto.PurchaseOrderRequest;
import com.jsp.warehousemanagement.responsedto.PurchaseOrderResponse;
import com.jsp.warehousemanagement.service.PurchaseOrderService;
import com.jsp.warehousemanagement.utility.ResponseStructure;
@RestController
@RequestMapping("/api/v2")
public class PurchaseOrderController {
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	@PostMapping("/inventories/{productId}/purchaseOrders")
	public ResponseEntity<ResponseStructure<PurchaseOrderResponse>> createPurchaseOrder(@RequestBody PurchaseOrderRequest purchaseOrderRequest,
			@PathVariable int productId){
		return purchaseOrderService.createPurchaseOrder(purchaseOrderRequest,productId);
		
		
	}

}
