package com.jsp.warehousemanagement.serviceimpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehousemanagement.entity.Client;
import com.jsp.warehousemanagement.entity.WareHouse;
import com.jsp.warehousemanagement.exception.ClientNotFoundByIdException;
import com.jsp.warehousemanagement.exception.WarehouseNotFoundByIdException;
import com.jsp.warehousemanagement.mapper.ClientMapper;
import com.jsp.warehousemanagement.repository.ClientRepository;
import com.jsp.warehousemanagement.requestdto.ClientRequest;
import com.jsp.warehousemanagement.responsedto.ApiKeyResponse;
import com.jsp.warehousemanagement.responsedto.WareHouseResponse;
import com.jsp.warehousemanagement.service.ClientService;
import com.jsp.warehousemanagement.utility.ResponseStructure;

import jakarta.validation.Valid;
@Service
public class ClientServiceImpl implements ClientService {

	@Autowired

	private ClientMapper clientMapper;
	
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public ResponseEntity<ResponseStructure<ApiKeyResponse>> registerClient(@Valid ClientRequest clientRequest) {
		// TODO Auto-generated method stub
		String apiKey = UUID.randomUUID().toString();//apiKey->

		Client client = clientMapper.mapToClient(clientRequest, new Client());
		client.setApiKey(apiKey);

		client = clientRepository.save(client);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<ApiKeyResponse>().setStatusCode(HttpStatus.CREATED.value())
						.setMessage("Client details created and apikey generated ").setData(clientMapper.mapToClientResponse(client)));

	}

	@Override
	public ResponseEntity<ResponseStructure<ApiKeyResponse>> updateClient(@Valid ClientRequest clientRequest,
			int clientId) {
		return clientRepository.findById(clientId).<ResponseEntity<ResponseStructure<ApiKeyResponse>>>map(exClient -> {

			exClient = clientMapper.mapToClient(clientRequest, exClient);

			Client client = clientRepository.save(exClient);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<ApiKeyResponse>()
							.setStatusCode(HttpStatus.OK.value())
							.setMessage("Client Details Updated")
							.setData(clientMapper.mapToClientResponse(client)));
		}).orElseThrow(()-> new ClientNotFoundByIdException("Client Not Found"));
	}
}
