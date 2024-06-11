package com.jsp.warehousemanagement.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehousemanagement.entity.Admin;
import com.jsp.warehousemanagement.entity.WareHouse;
import com.jsp.warehousemanagement.exception.AdminNotFindByEmailException;
import com.jsp.warehousemanagement.exception.WarehouseNotFoundByIdException;
import com.jsp.warehousemanagement.mapper.WareHouseMapper;
import com.jsp.warehousemanagement.repository.WareHouseRepository;
import com.jsp.warehousemanagement.requestdto.WareHouseRequest;
import com.jsp.warehousemanagement.responsedto.AdminResponse;
import com.jsp.warehousemanagement.responsedto.WareHouseResponse;
import com.jsp.warehousemanagement.service.WareHouseService;
import com.jsp.warehousemanagement.serviceimpl.WareHouseServiceImpl.WareHouseNotFoundByIdException;
import com.jsp.warehousemanagement.utility.ResponseStructure;

import jakarta.validation.Valid;



@Service
public class WareHouseServiceImpl implements WareHouseService {

	public class WareHouseNotFoundByIdException {

	}

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

	@Override
//	public ResponseEntity<ResponseStructure<WareHouseResponse>> updateWareHouse(
//			@Valid WareHouseRequest wareHouseRequest, int warehouseId) {
//		// TODO Auto-generated method stub
//		return wareHouseRepository.findById(warehouseId).map(exWareHouse->{
//			WareHouse wh = wareHouseMapper.mapToWareHouse(wareHouseRequest, exWareHouse);
//
//					WareHouse updatedn = wareHouseRepository.save(wh);
//
//					return ResponseEntity.status(HttpStatus.OK)
//							.body(new ResponseStructure<AdminResponse>()
//									.setStatusCode(HttpStatus.OK.value())
//									.setMessage("WareHouse Updated")
//									.setData(adminMapper.mapToAdminResponse(updatedAdmin)));
//				}).orElseThrow(()-> new WareHouseNotFoundByIdException("WareHouse not found"));
//				
//	}
	
	public ResponseEntity<ResponseStructure<WareHouseResponse>> updateWareHouse(
	        @Valid WareHouseRequest wareHouseRequest, int warehouseId) {
		return	wareHouseRepository.findById(warehouseId)
				.<ResponseEntity<ResponseStructure<WareHouseResponse>>>map(exWarehouse -> {

			exWarehouse = wareHouseMapper.mapToWareHouse(wareHouseRequest, exWarehouse);

			WareHouse warehouse = wareHouseRepository.save(exWarehouse);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<WareHouseResponse>()
							.setStatusCode(HttpStatus.OK.value())
							.setMessage("Warehouse Updated")
							.setData(wareHouseMapper.mapToWareHouseResponse(warehouse)));
		}).orElseThrow(()-> new WarehouseNotFoundByIdException("Warehouse Not Found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> findWarehouseById(int warehouseId) {
			
		return wareHouseRepository.findById(warehouseId).
				<ResponseEntity<ResponseStructure<WareHouseResponse>>>map(warehouse->{
			
			return ResponseEntity.status(HttpStatus.FOUND)
					.body(new ResponseStructure<WareHouseResponse>()
							.setStatusCode(HttpStatus.FOUND.value())
							.setMessage("Warehouse Found")
							.setData(wareHouseMapper.mapToWareHouseResponse(warehouse)));
		}).orElseThrow(()-> new WarehouseNotFoundByIdException("Warehouse not found by Id"));
	}
}



