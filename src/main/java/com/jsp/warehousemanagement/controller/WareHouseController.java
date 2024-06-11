package com.jsp.warehousemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehousemanagement.requestdto.AdminRequest;
import com.jsp.warehousemanagement.requestdto.WareHouseRequest;
import com.jsp.warehousemanagement.responsedto.AdminResponse;
import com.jsp.warehousemanagement.responsedto.WareHouseResponse;
import com.jsp.warehousemanagement.service.WareHouseService;
import com.jsp.warehousemanagement.utility.ResponseStructure;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/v2")
public class WareHouseController {
	@Autowired
	private WareHouseService wareHouseService;
	
//	@GetMapping("/warehouses")
//public String createWareHouse(){
//		return "warehouse found";
//	}
	@PreAuthorize("hasAuthority('CREATE_WAREHOUSE')")
	@PostMapping("/warehouses")
	public ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(@RequestBody @Valid
			WareHouseRequest wareHouseRequest){
		return wareHouseService.createWareHouse(wareHouseRequest);
	}
	@PreAuthorize("hasAuthority('UPDATE_WAREHOUSE')")
	@PutMapping("/warehouses/{warehouseId}")
	public ResponseEntity<ResponseStructure<WareHouseResponse>> updateWareHouse(@RequestBody @Valid
			WareHouseRequest wareHouseRequest,@PathVariable int warehouseId){
		return wareHouseService.updateWareHouse(wareHouseRequest,warehouseId);
	
	}
	
	@PreAuthorize("hasAuthority('READ')")
	@GetMapping("/warehouses/{warehouseId}")
	public ResponseEntity<ResponseStructure<WareHouseResponse>> findWarehouseById(@PathVariable int warehouseId){
		return wareHouseService.findWarehouseById(warehouseId);
	}

}

