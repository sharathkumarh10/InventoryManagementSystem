package com.jsp.warehousemanagement.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.warehousemanagement.entity.Inventory;
import com.jsp.warehousemanagement.entity.Stock;
import com.jsp.warehousemanagement.requestdto.InventoryRequest;
import com.jsp.warehousemanagement.responsedto.StockResponse;
import com.jsp.warehousemanagement.responsedto.InventoryResponse;
@Component
public class InventoryMapper {
	@Autowired
	private StockMapper stockMapper;
	
	public Inventory mapToInventory(InventoryRequest inventoryRequest,Inventory inventory) {
		inventory.setProductTitle(inventoryRequest.getProductTitle());
		inventory.setLengthInMetres(inventoryRequest.getLengthInMetres());
		inventory.setBreadthInMetres(inventoryRequest.getBreadthInMetres());
		inventory.setHeightInMetres(inventoryRequest.getHeightInMetres());
		inventory.setWeightInKg(inventoryRequest.getWeightInKg());
		inventory.setMaterialType(inventoryRequest.getMaterialType());
		inventory.setSellerId(inventoryRequest.getSellerId());
		return inventory;
	
	}
	
	public InventoryResponse mapToInventoryResponse(Inventory inventory) {
		return InventoryResponse.builder()
				.productId(inventory.getProductId())
				.productTitle(inventory.getProductTitle())
				.lengthInMetres(inventory.getLengthInMetres())
				.breadthInMetres(inventory.getBreadthInMetres())
				.heightInMetres(inventory.getHeightInMetres())
				.materialType(inventory.getMaterialType())
				.restockedAt(inventory.getRestockedAt())
				.sellerId(inventory.getSellerId())
				.weightInKg(inventory.getWeightInKg())
				
				.build();
	}
	
	public InventoryResponse mapToInventoryResponse(Inventory inventory , Stock stock) {

		return InventoryResponse.builder()
				.productId(inventory.getProductId())
				.productTitle(inventory.getProductTitle())
				.lengthInMetres(inventory.getLengthInMetres())
				.breadthInMetres(inventory.getBreadthInMetres())
				.heightInMetres(inventory.getHeightInMetres())
				.weightInKg(inventory.getWeightInKg())
				.materialType(inventory.getMaterialType())
				.restockedAt(inventory.getRestockedAt())
				.sellerId(inventory.getSellerId())
				.stock(stockMapper.mapToStockResponse(stock))
				.build();

	}

}
