package com.jsp.warehousemanagement.service;

import org.springframework.http.ResponseEntity;

import com.jsp.warehousemanagement.request.WareHouseRequest;
import com.jsp.warehousemanagement.responsedto.WareHouseResponse;
import com.jsp.warehousemanagement.utility.ResponseStructure;

public interface WareHouseService {
	
	public ResponseEntity<ResponseStructure<WareHouseResponse>> createSuperAdmin(
			WareHouseRequest wareHouseRequest);

}
