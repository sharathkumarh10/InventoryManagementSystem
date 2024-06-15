package com.jsp.warehousemanagement.mapper;

import org.springframework.stereotype.Component;

import com.jsp.warehousemanagement.entity.StorageType;

import com.jsp.warehousemanagement.requestdto.StorageTypeRequest;
import com.jsp.warehousemanagement.responsedto.StorageTypeResponse;

@Component
public class StorageTypeMapper {

	public StorageType mapToStorageType(StorageTypeRequest storageTypeRequest, StorageType storageType) {
		storageType.setLengthInMetres(storageTypeRequest.getLengthInMetres());
		storageType.setBreadthInMetres(storageTypeRequest.getBreadthInMetres());
		storageType.setHeightInMetres(storageTypeRequest.getHeightInMetres());
		storageType.setCapacityInkg(storageTypeRequest.getCapacityInkg());
		return storageType;

	}

	public StorageTypeResponse mapToStorageTypeResponse(StorageType storageType) {
		return StorageTypeResponse.builder().storageTypeId(storageType.getStorageTypeId())
				.lengthInMetres(storageType.getLengthInMetres()).breadthInMetres(storageType.getBreadthInMetres())
				.heightInMetres(storageType.getHeightInMetres()).capacityInkg(storageType.getCapacityInkg())
				.unitsAvailable(storageType.getUnitsAvailable()).build();

	}
}
