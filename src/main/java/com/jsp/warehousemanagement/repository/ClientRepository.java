package com.jsp.warehousemanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehousemanagement.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	Optional<Client> findByEmail(String username);

}
