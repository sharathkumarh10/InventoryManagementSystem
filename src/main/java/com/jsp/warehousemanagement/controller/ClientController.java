package com.jsp.warehousemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehousemanagement.requestdto.ClientRequest;
import com.jsp.warehousemanagement.requestdto.WareHouseRequest;
import com.jsp.warehousemanagement.responsedto.ClientResponse;
import com.jsp.warehousemanagement.responsedto.WareHouseResponse;
import com.jsp.warehousemanagement.service.ClientService;
import com.jsp.warehousemanagement.utility.ResponseStructure;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/v2")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@PostMapping("/client")
	public ResponseEntity<ResponseStructure<ClientResponse>> createWareHouse(@RequestBody @Valid

			ClientRequest clientRequest){
		return clientService.registerClient(clientRequest);
	}
	
	

}
