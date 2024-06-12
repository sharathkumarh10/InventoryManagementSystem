package com.jsp.warehousemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehousemanagement.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {


}
