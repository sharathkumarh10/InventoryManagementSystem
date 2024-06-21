package com.jsp.warehousemanagement.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehousemanagement.entity.Inventory;
import com.jsp.warehousemanagement.entity.PurchaseOrder;
import com.jsp.warehousemanagement.entity.Stock;
import com.jsp.warehousemanagement.exception.InventoryNotFoundByIdException;
import com.jsp.warehousemanagement.exception.InventoryNotFoundException;
import com.jsp.warehousemanagement.exception.OutOfStockException;
import com.jsp.warehousemanagement.mapper.PurchaseOrderMapper;
import com.jsp.warehousemanagement.repository.InventoryRepo;
import com.jsp.warehousemanagement.repository.PurchaseOrderRepo;
import com.jsp.warehousemanagement.requestdto.PurchaseOrderRequest;
import com.jsp.warehousemanagement.responsedto.PurchaseOrderResponse;
import com.jsp.warehousemanagement.responsedto.WareHouseResponse;
import com.jsp.warehousemanagement.service.PurchaseOrderService;
import com.jsp.warehousemanagement.utility.ResponseStructure;

@Service
public class PurchaseOrerServiceImpl implements PurchaseOrderService {

	@Autowired
	private PurchaseOrderRepo purchaseOrderRepo;
	@Autowired
	private InventoryRepo inventoryRepo;
	@Autowired
	private PurchaseOrderMapper purchaseOrderMapper;

	@Override
	public ResponseEntity<ResponseStructure<PurchaseOrderResponse>> createPurchaseOrder(
			PurchaseOrderRequest purchaseOrderRequest, int productId) {

		Inventory inventory = inventoryRepo.findById(productId)
				.orElseThrow(() -> new InventoryNotFoundByIdException("Inventory Not Found"));

		PurchaseOrder purchaseOrder = purchaseOrderMapper.mapToPurchaseOrder(purchaseOrderRequest, new PurchaseOrder());

		int oldQuantity = inventory.getStock().getFirst().getQuantity();

		if (oldQuantity >= purchaseOrderRequest.getOrderQuantity()) {

			int newQuantity = oldQuantity - purchaseOrderRequest.getOrderQuantity();
			Stock stock = inventory.getStock().getFirst();
			stock.setQuantity(newQuantity);

			inventory.getStock().add(stock);
			inventoryRepo.save(inventory);

			purchaseOrder.setInventories(List.of(inventory));
			purchaseOrder.setStatus("Active");
			

			purchaseOrder=purchaseOrderRepo.save(purchaseOrder);

		}
		else {
			throw new OutOfStockException("Out of stock");
		}

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<PurchaseOrderResponse>().setStatusCode(HttpStatus.CREATED.value())
						.setMessage("Purchase Order Created")
						.setData(purchaseOrderMapper.mapToPurchaseOrderResponse(purchaseOrder)));

		// TODO Auto-generated method stubF

	}
}
