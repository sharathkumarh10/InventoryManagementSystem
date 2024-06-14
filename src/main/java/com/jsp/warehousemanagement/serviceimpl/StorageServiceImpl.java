package com.jsp.warehousemanagement.serviceimpl;

import com.jsp.warehousemanagement.entity.Storage;

import com.jsp.warehousemanagement.entity.WareHouse;
import com.jsp.warehousemanagement.exception.StorageNotFoundByIdException;
import com.jsp.warehousemanagement.exception.WarehouseNotFoundByIdException;
import com.jsp.warehousemanagement.mapper.StorageMapper;
import com.jsp.warehousemanagement.repository.StorageRepository;
import com.jsp.warehousemanagement.repository.WareHouseRepository;
import com.jsp.warehousemanagement.requestdto.StorageRequest;
import com.jsp.warehousemanagement.responsedto.StorageResponse;
import com.jsp.warehousemanagement.service.StorageService;
import com.jsp.warehousemanagement.utility.ResponseStructure;
import com.jsp.warehousemanagement.utility.SimpleStructure;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StorageServiceImpl implements StorageService {
	@Autowired
	private StorageRepository storageRepository;

	@Autowired
	private WareHouseRepository wareHouseRespository;

	@Autowired
	private StorageMapper storageMapper;

	
	
	public ResponseEntity<SimpleStructure<String>> createStorage(StorageRequest storageRequest,
			int wareHouseId, int noOfStorageUnits) {

		WareHouse wareHouse =  wareHouseRespository.findById(wareHouseId).orElseThrow(()-> new WarehouseNotFoundByIdException("warehouse not found by id"));

			List<Storage> storages = new ArrayList<Storage>();
			
			int count = 0;

			while(noOfStorageUnits > 0) {
			
			Storage storage  = storageMapper.mapToStorage(storageRequest, new Storage());
			
			
			
			storage.setMaxAdditionalWeightInKg(storageRequest.getCapacityInkg());
			storage.setAvailableAreaInMetre(storageRequest.getLengthInMetres() * storageRequest.getBreadthInMetres() * storageRequest.getHeightInMetres());
			
			wareHouse.setTotalCapacity(storageRequest.getCapacityInkg() * noOfStorageUnits +  wareHouse.getTotalCapacity());
			storage.setWareHouse(wareHouse);
			
			
			storages.add(storage);
			count++;
			noOfStorageUnits --;
		}
		
		storageRepository.saveAll(storages);
		wareHouseRespository.save(wareHouse);
		

		
		return ResponseEntity.status(HttpStatus.CREATED).body(new SimpleStructure<String>()
				.setStatus(HttpStatus.CREATED.value())
				.setMesssage(""+count + " Storages created"));

	}



	@Override
	public ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(int storageId,
			StorageRequest storageRequest) {
		// TODO Auto-generated method stub

		return storageRepository.findById(storageId).map(existingStorage -> {
			
			existingStorage = storageMapper.mapToStorage(storageRequest, existingStorage);
			existingStorage.setAvailableAreaInMetre(storageRequest.getLengthInMetres() * storageRequest.getBreadthInMetres() * storageRequest.getHeightInMetres());
			storageRepository.save(existingStorage);
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<StorageResponse>()
					.setStatusCode(HttpStatus.OK.value())
					.setMessage("Storage updated")
					.setData(storageMapper.mapToStorageResponse(existingStorage)));
					
			
		}).orElseThrow(()-> new StorageNotFoundByIdException("storage Not Found"));
	}

}
