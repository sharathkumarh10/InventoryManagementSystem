package com.jsp.warehousemanagement.mapper;

import org.springframework.stereotype.Component;

import com.jsp.warehousemanagement.entity.Address;

import com.jsp.warehousemanagement.requestdto.AddressRequest;

import com.jsp.warehousemanagement.responsedto.AddressResponse;

@Component
public class AddressMapper {

	public Address mapToAddress(AddressRequest addressRequest, Address address) {
		address.setAddressLine(addressRequest.getAddressLine());
		address.setCity(addressRequest.getCity());
		address.setState(addressRequest.getState());
		address.setCountry(addressRequest.getCountry());
		address.setPincode(addressRequest.getPincode());
		address.setLongitude(addressRequest.getLongitude());
		address.setLatitude(addressRequest.getLatitude());
		return address;
	}

	public AddressResponse mapToAddressResponse(Address address) {
		return AddressResponse.builder().addressId(address.getAddressId()).city(address.getCity())
				.state(address.getState()).country(address.getCountry()).pincode(address.getPincode())
				.longitude(address.getLongitude()).latitude(address.getLatitude()).build();
	}
}
