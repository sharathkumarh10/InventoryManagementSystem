package com.jsp.warehousemanagement.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.warehousemanagement.entity.Stock;

import com.jsp.warehousemanagement.responsedto.StockResponse;
@Component
public class StockMapper {

	@Autowired
	private StorageMapper storageMapper;
	
	public StockResponse mapToStockResponse(Stock stock) {
		
		return StockResponse.builder()
				.stockId(stock.getStockId())
				.quantity(stock.getQuantity())
				.storage(storageMapper.mapToStorageResponse(stock.getStorage()))
				.build();
	}
}
