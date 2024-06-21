package com.jsp.warehousemanagement.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehousemanagement.entity.Client;
import com.jsp.warehousemanagement.entity.Inventory;
import com.jsp.warehousemanagement.entity.Stock;
import com.jsp.warehousemanagement.entity.Storage;
import com.jsp.warehousemanagement.exception.BatchNotFoundException;
import com.jsp.warehousemanagement.exception.ClientNotFoundByIdException;
import com.jsp.warehousemanagement.exception.InventoriesNotFoundException;
import com.jsp.warehousemanagement.exception.InventoryNotFoundByIdException;
import com.jsp.warehousemanagement.exception.SpaceOrWeightNotAvailableException;
import com.jsp.warehousemanagement.exception.StorageNotFoundByIdException;
import com.jsp.warehousemanagement.mapper.InventoryMapper;
import com.jsp.warehousemanagement.mapper.StockMapper;
import com.jsp.warehousemanagement.repository.ClientRepository;
import com.jsp.warehousemanagement.repository.InventoryRepo;
import com.jsp.warehousemanagement.repository.StockRepo;
import com.jsp.warehousemanagement.repository.StorageRepository;
import com.jsp.warehousemanagement.requestdto.InventoryRequest;
import com.jsp.warehousemanagement.responsedto.InventoryResponse;
import com.jsp.warehousemanagement.responsedto.StockResponse;
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
	
	@Autowired
	private StockRepo stockRepo;
	@Autowired
	private StockMapper stockMapper;
	
//	private Stock stock;
	
	


		@Override
		public ResponseEntity<ResponseStructure<InventoryResponse>> createInventory(InventoryRequest inventoryRequest
				,int clientId, int storageId,int quantity)
				 {

			Storage storage = storageRepo.findById(storageId).orElseThrow(()-> new StorageNotFoundByIdException("storage Not Found"));

			Inventory inventory = inventoryRepo.save(inventoryMapper.mapToInventory(inventoryRequest, new Inventory()));

			Client client = clientRepo.findById(clientId).orElseThrow(()-> new ClientNotFoundByIdException("Client Not Found"));

			inventory.setRestockedAt(LocalDate.now());
			inventory.setClient(client);
			
			 Stock stock=new Stock();
			


			storage.setMaxAdditionalWeightInKg(storage.getMaxAdditionalWeightInKg()  - inventoryRequest.getWeightInKg()*quantity);
			storage.setAvailableAreaInMetre(((storage.getAvailableAreaInMetre())
					-(inventory.getLengthInMetres() * inventory.getBreadthInMetres() * inventory.getHeightInMetres())));
			storage.setSellerId(inventoryRequest.getSellerId());

			Inventory inventory1 = inventoryRepo.save(inventory);
			
			
					
			 Storage storage1=storageRepo.save(storage);
			 
			 stock.setInventory(inventory1);
			 stock.setStorage(storage1);
			 stock.setQuantity(quantity);
			 stock=stockRepo.save(stock);
					
			clientRepo.save(client);

			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseStructure<InventoryResponse>()
							.setStatusCode(HttpStatus.CREATED.value())
							.setMessage("Inventory created")
							.setData(inventoryMapper.mapToInventoryResponse(inventory,stock)));
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

		List<InventoryResponse> inventoryResponses = inventoryRepo.findAll().stream()
				.map(inventories->inventoryMapper.mapToInventoryResponse(inventories)).toList();

		if(inventoryResponses.isEmpty())
			throw new InventoriesNotFoundException("No inventories are present in the database");

		return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<InventoryResponse>>()
				.setStatusCode(HttpStatus.FOUND.value())
				.setMessage("Found and displayed")
				.setData(inventoryResponses));


	}



		@Override
		public ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(InventoryRequest inventoryRequest,int productId)
				{
			
			return inventoryRepo.findById(productId).map(inventory -> {
				
				Stock stock = stockRepo.findByInventory_ProductId(productId).orElseThrow(()->new BatchNotFoundException("stock not found"));
				
					int oldQuantity = stock.getQuantity();
					double oldLength = inventory.getLengthInMetres();
					double oldBreadth = inventory.getBreadthInMetres();
					double oldHeight = inventory.getHeightInMetres();
					
				 double originalWeight = inventory.getWeightInKg() * oldQuantity;
				 double originalArea = inventory.getBreadthInMetres() * inventory.getHeightInMetres() * inventory.getLengthInMetres();
				 
				 Storage storage = stock.getStorage();
				 
				Inventory updatedInventory = inventoryMapper.mapToInventory(inventoryRequest, inventory);
				 

				   double newWeight = updatedInventory.getWeightInKg() * oldQuantity;
				   double newArea = updatedInventory.getBreadthInMetres() * updatedInventory.getHeightInMetres() * updatedInventory.getLengthInMetres();
				   
				  if((oldLength != updatedInventory.getLengthInMetres() || oldBreadth != updatedInventory.getBreadthInMetres() || oldHeight != updatedInventory.getHeightInMetres())
						  || originalWeight != newWeight)
						  {
									if(storage.getAvailableAreaInMetre()>0 && storage.getMaxAdditionalWeightInKg() >0)
									{
										
										storage.setMaxAdditionalWeightInKg(storage.getMaxAdditionalWeightInKg() + originalWeight - newWeight);
								        storage.setAvailableAreaInMetre(storage.getAvailableAreaInMetre() + originalArea - newArea);  
									}
									
									else
									{
										throw new SpaceOrWeightNotAvailableException("No Available Area or Capacity of Storage Full");
									}

						  }
				  
				  	updatedInventory = inventoryRepo.save(updatedInventory);
					stock.setInventory(updatedInventory);
					stock.setStorage(storage);
					stockRepo.save(stock); 
					storageRepo.save(storage);
		
			    return ResponseEntity.status(HttpStatus.OK)
			            .body(new ResponseStructure<InventoryResponse>()
			                    .setData(inventoryMapper.mapToInventoryResponse(updatedInventory, stock))
			                    .setMessage("Inventory updated")
			                    .setStatusCode(HttpStatus.OK.value()));
				
			}).orElseThrow(() -> new InventoryNotFoundByIdException("Inventory not found"));
		}
		// TODO Auto-generated method stub
		@Override
		public ResponseEntity<ResponseStructure<List<StockResponse>>> updateInventoryByQuantity(int storageId, int productId, int quantity) {
		    Inventory inventory = inventoryRepo.findById(productId)
		            .orElseThrow(() -> new InventoryNotFoundByIdException("Inventory not found"));

		    Storage storage = storageRepo.findById(storageId)
		            .orElseThrow(() -> new StorageNotFoundByIdException("Storage not found"));

		    List<Stock> stockes = stockRepo.findByStorageAndInventory(storage, inventory);
		    if (stockes.isEmpty()) {
		        throw new BatchNotFoundException("Batches not found");
		    }

		    for (Stock stock : stockes) {
		        stock.setQuantity(quantity);
		        inventory.setRestockedAt(LocalDate.now());
		        stockRepo.save(stock);
		    }

		    List<StockResponse> batchResponses = stockes.stream()
		            .map(stockMapper::mapToStockResponse)
		            .collect(Collectors.toList());
		    
		    return ResponseEntity.status(HttpStatus.OK)
		    		.body(new ResponseStructure<List<StockResponse>>()
		    				.setStatusCode(HttpStatus.OK.value())
		    				.setMessage("quantity updated")
		    				.setData(batchResponses));
		    			
		}
	}

	



