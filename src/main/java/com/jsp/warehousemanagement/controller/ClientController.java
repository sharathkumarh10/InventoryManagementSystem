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
import com.jsp.warehousemanagement.utility.ErrorStructure;
import com.jsp.warehousemanagement.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/v2")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@Operation(description = "the endpoint for registering the client to database", responses = {
			@ApiResponse(responseCode = "201", description = "client registered"),
			@ApiResponse(responseCode = "400", description = "client already registered", content = {
					@Content(schema = @Schema(oneOf = ErrorStructure.class)) }) })
	
	@PostMapping("/client/register")
	public ResponseEntity<ResponseStructure<ApiKeyResponse>> registerClient(@RequestBody @Valid

			ClientRequest clientRequest){
		return clientService.registerClient(clientRequest);
	}
	
	@PutMapping("/client/{clientId}/clients")
	public ResponseEntity<ResponseStructure<ApiKeyResponse>> updateClient(@RequestBody @Valid

			ClientRequest clientRequest,@PathVariable int clientId){
		return clientService.updateClient(clientRequest,clientId);
	}
	
	

}
