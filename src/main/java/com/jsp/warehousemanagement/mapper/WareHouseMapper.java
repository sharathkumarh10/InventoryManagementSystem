package com.jsp.warehousemanagement.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.warehousemanagement.entity.Address;
import com.jsp.warehousemanagement.entity.WareHouse;
import com.jsp.warehousemanagement.requestdto.WareHouseRequest;
import com.jsp.warehousemanagement.responsedto.AddressResponse;
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
				.totalCapacity(wareHouse.getTotalCapacity())
				.build();
	}
	
	@Autowired
	private AddressMapper addressMapper;
	
	public WareHouseResponse mapToWareHouseAddress(WareHouse warehouse, Address address) {
		WareHouseResponse response = mapToWareHouseResponse(warehouse);
		 response.setAddressResponse(addressMapper.mapToAddressResponse(address));
		
		return response;
	}

}
