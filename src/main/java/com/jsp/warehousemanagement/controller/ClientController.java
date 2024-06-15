package com.jsp.warehousemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehousemanagement.requestdto.ClientRequest;

import com.jsp.warehousemanagement.responsedto.ApiKeyResponse;

import com.jsp.warehousemanagement.service.ClientService;
import com.jsp.warehousemanagement.utility.ResponseStructure;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/v2")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@PostMapping("/clients")
	public ResponseEntity<ResponseStructure<ApiKeyResponse>> registerClient(@RequestBody @Valid

			ClientRequest clientRequest){
		return clientService.registerClient(clientRequest);
	}
	
	@PutMapping("/clients/{clientId}")
	public ResponseEntity<ResponseStructure<ApiKeyResponse>> updateClient(@RequestBody @Valid

			ClientRequest clientRequest,@PathVariable int clientId){
		return clientService.updateClient(clientRequest,clientId);
	}
	
	

}
