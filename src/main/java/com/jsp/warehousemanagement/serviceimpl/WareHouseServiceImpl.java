package com.jsp.warehousemanagement.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehousemanagement.entity.WareHouse;
import com.jsp.warehousemanagement.mapper.WareHouseMapper;
import com.jsp.warehousemanagement.repository.WareHouseRepository;
import com.jsp.warehousemanagement.requestdto.WareHouseRequest;
import com.jsp.warehousemanagement.responsedto.WareHouseResponse;
import com.jsp.warehousemanagement.service.WareHouseService;
import com.jsp.warehousemanagement.utility.ResponseStructure;



@Service
public class WareHouseServiceImpl implements WareHouseService {

	@Autowired
	private WareHouseRepository wareHouseRepository;
	
	@Autowired
	private WareHouseMapper wareHouseMapper;
	
	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse( WareHouseRequest wareHouseRequest) {

			WareHouse wareHouse = wareHouseMapper.mapToWareHouse(wareHouseRequest, new WareHouse());
			wareHouseRepository.save(wareHouse);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<WareHouseResponse>()
					.setStatusCode(HttpStatus.CREATED.value())
					.setMessage("WareHouse created")
					.setData(wareHouseMapper.mapToWareHouseResponse(wareHouse)));
	}
}



