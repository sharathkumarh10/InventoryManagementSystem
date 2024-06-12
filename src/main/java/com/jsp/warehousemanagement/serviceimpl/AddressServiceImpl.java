package com.jsp.warehousemanagement.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehousemanagement.entity.Address;
import com.jsp.warehousemanagement.entity.Admin;
import com.jsp.warehousemanagement.entity.WareHouse;
import com.jsp.warehousemanagement.enums.AdminType;
import com.jsp.warehousemanagement.exception.AddressNotFoundByIdException;
import com.jsp.warehousemanagement.exception.WarehouseNotFoundByIdException;
import com.jsp.warehousemanagement.mapper.AddressMapper;
import com.jsp.warehousemanagement.repository.AddressRepo;
import com.jsp.warehousemanagement.repository.WareHouseRepository;
import com.jsp.warehousemanagement.requestdto.AddressRequest;
import com.jsp.warehousemanagement.responsedto.AddressResponse;
import com.jsp.warehousemanagement.responsedto.WareHouseResponse;
import com.jsp.warehousemanagement.service.AddressService;
import com.jsp.warehousemanagement.utility.ResponseStructure;

import jakarta.validation.Valid;
@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	private WareHouseRepository warehouseRepo;
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private AddressRepo addressRepo;

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> createAddress(@Valid AddressRequest addressRequest,
			int warehouseId) {
		// TODO Auto-generated method stub
		return warehouseRepo.findById(warehouseId).map(wareHouse -> {
			Address address = addressMapper.mapToAddress(addressRequest, new Address());
			
			address.setWareHouse(wareHouse);
			address=addressRepo.save(address);


			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseStructure<AddressResponse>().setStatusCode(HttpStatus.CREATED.value())
							.setMessage("Addres created").setData(addressMapper.mapToAddressResponse(address)));

		}).orElseThrow(() -> new WarehouseNotFoundByIdException("warehouse not found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(@Valid AddressRequest addressRequest,
			int addressId) {
		// TODO Auto-generated method stub
		return	addressRepo.findById(addressId)
				.<ResponseEntity<ResponseStructure<AddressResponse>>>map(exAddress -> {

			exAddress = addressMapper.mapToAddress(addressRequest, exAddress);

			Address address = addressRepo.save(exAddress);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<AddressResponse>()
							.setStatusCode(HttpStatus.OK.value())
							.setMessage("address Updated")
							.setData(addressMapper.mapToAddressResponse(address)));
		}).orElseThrow(()-> new AddressNotFoundByIdException("Addres Not Found By id"));
	}

}
