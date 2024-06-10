package com.jsp.warehousemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehousemanagement.request.AdminRequest;
import com.jsp.warehousemanagement.responsedto.AdminResponse;
import com.jsp.warehousemanagement.service.AdminService;
import com.jsp.warehousemanagement.utility.ErrorStructure;
import com.jsp.warehousemanagement.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v2")
@Tag(name="Admin Endpoint",description="contains all the endpoints that are related to admin entity")
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Operation(description="the endpoint for creating the superadmin to database",responses= {
			@ApiResponse(responseCode="201",description="Admin created"),
			@ApiResponse(responseCode="400",description="Invalid input",content= {
					@Content(schema=@Schema(oneOf=ErrorStructure.class))
			})
	})
	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(@RequestBody @Valid
			AdminRequest adminRequest){
		return adminService.createSuperAdmin(adminRequest);
	}
	
	@PostMapping("/warehouses/{warehouseId}/admins")
	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(@RequestBody @Valid
			AdminRequest adminRequest,@PathVariable int warehouseId){
		return adminService.createAdmin(adminRequest,warehouseId);
	}
}
