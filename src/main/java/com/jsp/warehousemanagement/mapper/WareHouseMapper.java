package com.jsp.warehousemanagement.mapper;

import org.springframework.stereotype.Component;

import com.jsp.warehousemanagement.entity.WareHouse;
import com.jsp.warehousemanagement.requestdto.WareHouseRequest;
import com.jsp.warehousemanagement.responsedto.WareHouseResponse;
@Component
public class WareHouseMapper {
	
	public WareHouse mapToWareHouse(WareHouseRequest  warehouseRequest,WareHouse wareHouse) {
		wareHouse.setName(warehouseRequest.getName());
		return wareHouse;
	}
	
	public WareHouseResponse mapToWareHouseResponse(WareHouse wareHouse) {
		return WareHouseResponse.builder()
				.warehouseId(wareHouse.getWarehouseId())
				
				.name(wareHouse.getName())
				.totalCapacity(0).build();
	}

}
