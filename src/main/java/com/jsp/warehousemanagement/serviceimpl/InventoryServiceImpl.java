package com.jsp.warehousemanagement.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehousemanagement.entity.Client;
import com.jsp.warehousemanagement.entity.Inventory;
import com.jsp.warehousemanagement.entity.Storage;
import com.jsp.warehousemanagement.exception.ClientNotFoundByIdException;
import com.jsp.warehousemanagement.exception.InventoriesNotFoundException;
import com.jsp.warehousemanagement.exception.InventoryNotFoundByIdException;
import com.jsp.warehousemanagement.exception.StorageNotFoundByIdException;
import com.jsp.warehousemanagement.mapper.InventoryMapper;
import com.jsp.warehousemanagement.repository.ClientRepository;
import com.jsp.warehousemanagement.repository.InventoryRepo;
import com.jsp.warehousemanagement.repository.StorageRepository;
import com.jsp.warehousemanagement.requestdto.InventoryRequest;
import com.jsp.warehousemanagement.responsedto.InventoryResponse;
import com.jsp.warehousemanagement.service.InventoryService;
import com.jsp.warehousemanagement.utility.ResponseStructure;
@Service
public class InventoryServiceImpl implements InventoryService{
	@Autowired
	private InventoryMapper inventoryMapper;
	
	@Autowired
	private StorageRepository storageRepo;
	
	@Autowired
	private InventoryRepo inventoryRepo;
	
	@Autowired
	private ClientRepository clientRepo;


		@Override
		public ResponseEntity<ResponseStructure<InventoryResponse>> createInventory(InventoryRequest inventoryRequest,int clientId, int storageId)
				 {

			Storage storage = storageRepo.findById(storageId).orElseThrow(()-> new StorageNotFoundByIdException("storage Not Found"));

			Inventory inventory = inventoryRepo.save(inventoryMapper.mapToInventory(inventoryRequest, new Inventory()));

			Client client = clientRepo.findById(clientId).orElseThrow(()-> new ClientNotFoundByIdException("Client Not Found"));

			storage.getInventories().add(inventory);
			inventory.setRestockedAt(LocalDate.now());
			inventory.setClient(client);


			storage.setMaxAdditionalWeightInKg(storage.getMaxAdditionalWeightInKg() * inventoryRequest.getQuantity() - inventoryRequest.getWeightInKg());
			storage.setAvailableAreaInMetre(((storage.getAvailableAreaInMetre())-(inventory.getLengthInMetres() * inventory.getBreadthInMetres() * inventory.getHeightInMetres())));
			storage.setSellerId(inventoryRequest.getSellerId());

			inventory = inventoryRepo.save(inventory);
			storageRepo.save(storage);
			clientRepo.save(client);

			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseStructure<InventoryResponse>()
							.setStatusCode(HttpStatus.CREATED.value())
							.setMessage("Inventory created")
							.setData(inventoryMapper.mapToInventoryResponse(inventory)));
		}
	

	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> findInventory(int productId) {
		// TODO Auto-generated method stub
		Inventory inventory = inventoryRepo.findById(productId).orElseThrow(()-> new InventoryNotFoundByIdException("No inventory found by the given id"));

		return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<InventoryResponse>()
				.setStatusCode(HttpStatus.FOUND.value())
				.setMessage("Inventories found and displayed")
				.setData(inventoryMapper.mapToInventoryResponse(inventory))
				);

	}

	@Override
	public ResponseEntity<ResponseStructure<List<InventoryResponse>>> findAllInventories() {

		List<InventoryResponse> inventoryResponses = inventoryRepo.findAll().stream().map(inventories->inventoryMapper.mapToInventoryResponse(inventories)).toList();

		if(inventoryResponses.isEmpty())
			throw new InventoriesNotFoundException("No inventories are present in the database");

		return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<InventoryResponse>>()
				.setStatusCode(HttpStatus.FOUND.value())
				.setMessage("Found and displayed")
				.setData(inventoryResponses));


	}
	}


