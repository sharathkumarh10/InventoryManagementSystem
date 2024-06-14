package com.jsp.warehousemanagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.warehousemanagement.requestdto.WareHouseRequest;
import com.jsp.warehousemanagement.responsedto.WareHouseResponse;
import com.jsp.warehousemanagement.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface WareHouseService {

	ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(@Valid WareHouseRequest wareHouseRequest);

	ResponseEntity<ResponseStructure<WareHouseResponse>> updateWareHouse(@Valid WareHouseRequest wareHouseRequest,int warehouseId);

	ResponseEntity<ResponseStructure<WareHouseResponse>> findWarehouseById(int warehouseId);

	ResponseEntity<ResponseStructure<List<WareHouseResponse>>> findAllWareHouses();

	ResponseEntity<ResponseStructure<List<WareHouseResponse>>> findWarehousesByCity(String city);
	
	

}
