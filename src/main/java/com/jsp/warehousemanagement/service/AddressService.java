package com.jsp.warehousemanagement.service;

import org.springframework.http.ResponseEntity;

import com.jsp.warehousemanagement.requestdto.AddressRequest;
import com.jsp.warehousemanagement.responsedto.AddressResponse;
import com.jsp.warehousemanagement.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface AddressService {

	ResponseEntity<ResponseStructure<AddressResponse>> createAddress(@Valid AddressRequest addressRequest,
			int warehouseId);

	ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(@Valid AddressRequest addressRequest,
			int addressId);

}
