package com.jsp.warehousemanagement.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehousemanagement.repository.WareHouseRepository;
import com.jsp.warehousemanagement.request.WareHouseRequest;
import com.jsp.warehousemanagement.responsedto.WareHouseResponse;
import com.jsp.warehousemanagement.service.WareHouseService;
import com.jsp.warehousemanagement.utility.ResponseStructure;
@Service
public class WareHouseServiceImpl implements WareHouseService {
	@Autowired
	private WareHouseRepository whRepo;
	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> createSuperAdmin(WareHouseRequest wareHouseRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
