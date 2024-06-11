package com.jsp.warehousemanagement.service;

import org.springframework.http.ResponseEntity;

import com.jsp.warehousemanagement.requestdto.AdminRequest;
import com.jsp.warehousemanagement.responsedto.AdminResponse;
import com.jsp.warehousemanagement.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface AdminService {

	public ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(
			AdminRequest adminRequest);

//	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(@Valid AdminRequest adminRequest);

	ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(@Valid AdminRequest adminRequest, int warehouseId);

	ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(@Valid AdminRequest adminRequest);

	

	ResponseEntity<ResponseStructure<AdminResponse>> updateAdminBySuperAdmin(AdminRequest adminRequest, int adminId);
}
