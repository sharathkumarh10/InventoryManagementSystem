package com.jsp.warehousemanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehousemanagement.entity.Address;


public interface AddressRepo extends JpaRepository<Address, Integer> {

	List<Address> findWarehousesByCity(String city);


}
