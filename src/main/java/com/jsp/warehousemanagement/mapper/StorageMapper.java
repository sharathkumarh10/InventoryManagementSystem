package com.jsp.warehousemanagement.mapper;

import org.springframework.stereotype.Component;

import com.jsp.warehousemanagement.entity.Storage;
import com.jsp.warehousemanagement.requestdto.StorageRequest;
import com.jsp.warehousemanagement.responsedto.StorageResponse;
@Component
public class StorageMapper {
	
	public Storage mapToStorage(StorageRequest storageRequest,Storage storage ) {
		
		storage.setBlockName(storageRequest.getBlockName());
		storage.setSection(storageRequest.getSection());
		storage.setLengthInMetres(storageRequest.getLengthInMetres());
		storage.setBreadthInMetres(storageRequest.getBreadthInMetres());
		storage.setHeightInMetres(storageRequest.getHeightInMetres());
		storage.setCapacityInkg(storageRequest.getCapacityInkg());
		storage.setMaterialType(storageRequest.getMaterialTypes());

		return storage;
	}

	public StorageResponse mapToStorageResponse(Storage storage) {
		return StorageResponse.builder()
				.storageId(storage.getStorageId())
				.blockName(storage.getBlockName())
				.section(storage.getSection())
				.capacityInKg(storage.getCapacityInkg())
				.materialTypes(storage.getMaterialType())
				.availableAreaInMetre(storage.getAvailableAreaInMetre())
				.maxAdditionalWeight(storage.getMaxAdditionalWeightInKg())
				.materialTypes(storage.getMaterialType())
				.build();
	}
}
