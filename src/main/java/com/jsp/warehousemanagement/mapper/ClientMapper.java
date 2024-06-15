package com.jsp.warehousemanagement.mapper;

import org.springframework.stereotype.Component;

import com.jsp.warehousemanagement.entity.Client;
import com.jsp.warehousemanagement.requestdto.ClientRequest;
import com.jsp.warehousemanagement.responsedto.ApiKeyResponse;
@Component
public class ClientMapper {
	
	
	public Client mapToClient(ClientRequest clientRequest,Client client) {
		client.setBusinessName(clientRequest.getBusinessName());
		client.setEmail(clientRequest.getEmail());
		client.setContactNumber(clientRequest.getContactNumber());
		return client;
	}

	public ApiKeyResponse mapToClientResponse(Client client) {
		return ApiKeyResponse.builder()
				
				.apiKey(client.getApiKey())
				.build();
	}
}
