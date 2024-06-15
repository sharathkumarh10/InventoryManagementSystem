package com.jsp.warehousemanagement.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehousemanagement.entity.StorageType;
import com.jsp.warehousemanagement.entity.WareHouse;
import com.jsp.warehousemanagement.exception.StorageTypeNotFoundByIdException;
import com.jsp.warehousemanagement.exception.WarehouseNotFoundByIdException;
import com.jsp.warehousemanagement.mapper.StorageTypeMapper;
import com.jsp.warehousemanagement.repository.StorageTypeRepo;
import com.jsp.warehousemanagement.requestdto.StorageTypeRequest;

import com.jsp.warehousemanagement.responsedto.StorageTypeResponse;
import com.jsp.warehousemanagement.responsedto.WareHouseResponse;
import com.jsp.warehousemanagement.service.StorageTypeService;
import com.jsp.warehousemanagement.utility.ResponseStructure;

@Service
public class StorageTypeServiceImpl implements StorageTypeService {
	@Autowired
	private StorageTypeRepo storageTypeRepo;
	@Autowired
	private StorageTypeMapper storageTypeMapper;

	@Override
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> createStorageType(
			StorageTypeRequest storageTypeRequest) {
		// TODO Auto-generated method stub
		StorageType storageType = storageTypeMapper.mapToStorageType(storageTypeRequest, new StorageType());
		storageType = storageTypeRepo.save(storageType);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<StorageTypeResponse>().setStatusCode(HttpStatus.CREATED.value())
						.setMessage("StorageType created")
						.setData(storageTypeMapper.mapToStorageTypeResponse(storageType)));
	}

	@Override
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> updateStorageType(
			StorageTypeRequest storageTypeRequest, int storageTypeId) {
		// TODO Auto-generated method stub
		return storageTypeRepo.findById(storageTypeId)
				.<ResponseEntity<ResponseStructure<StorageTypeResponse>>>map(exStorageType -> {

					exStorageType = storageTypeMapper.mapToStorageType(storageTypeRequest, exStorageType);

					StorageType storageType = storageTypeRepo.save(exStorageType);

					return ResponseEntity.status(HttpStatus.OK)
							.body(new ResponseStructure<StorageTypeResponse>().setStatusCode(HttpStatus.OK.value())
									.setMessage("StorageType Updated")
									.setData(storageTypeMapper.mapToStorageTypeResponse(storageType)));
				}).orElseThrow(() -> new StorageTypeNotFoundByIdException("StorageType Not Found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<StorageTypeResponse>>> findAllStorageType() {
		// TODO Auto-generated method stub

		List<StorageTypeResponse> storageTypeList = storageTypeRepo.findAll().stream()
				.map(storageType -> storageTypeMapper.mapToStorageTypeResponse(storageType)).toList();

		return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<StorageTypeResponse>>()
				.setStatusCode(HttpStatus.FOUND.value()).setMessage("StoarageType Found").setData(storageTypeList));

	}

}
