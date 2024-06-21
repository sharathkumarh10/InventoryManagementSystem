package com.jsp.warehousemanagement.service;

import org.springframework.http.ResponseEntity;

import com.jsp.warehousemanagement.requestdto.PurchaseOrderRequest;
import com.jsp.warehousemanagement.responsedto.PurchaseOrderResponse;
import com.jsp.warehousemanagement.utility.ResponseStructure;

public interface PurchaseOrderService {

	ResponseEntity<ResponseStructure<PurchaseOrderResponse>> createPurchaseOrder(
			PurchaseOrderRequest purchaseOrderRequest, int productId);

}
