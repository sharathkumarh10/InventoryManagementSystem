package com.jsp.warehousemanagement.service;

import org.springframework.http.ResponseEntity;

import com.jsp.warehousemanagement.requestdto.ClientRequest;
import com.jsp.warehousemanagement.responsedto.ApiKeyResponse;
import com.jsp.warehousemanagement.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface ClientService {

	ResponseEntity<ResponseStructure<ApiKeyResponse>> registerClient(@Valid ClientRequest clientRequest);

	ResponseEntity<ResponseStructure<ApiKeyResponse>> updateClient(@Valid ClientRequest clientRequest, int clientId);

}
