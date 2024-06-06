package com.jsp.warehousemanagement.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehousemanagement.entity.Admin;
import com.jsp.warehousemanagement.enums.AdminType;
import com.jsp.warehousemanagement.enums.Privilege;
import com.jsp.warehousemanagement.exception.IllegalOperationException;
import com.jsp.warehousemanagement.mapper.AdminMapper;
import com.jsp.warehousemanagement.repository.AdminRepository;
import com.jsp.warehousemanagement.request.AdminRequest;
import com.jsp.warehousemanagement.responsedto.AdminResponse;
import com.jsp.warehousemanagement.service.AdminService;
import com.jsp.warehousemanagement.utility.ResponseStructure;
@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private AdminMapper adminMapper;
	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(AdminRequest adminRequest) {
		if(adminRepo.existsByAdminType(AdminType.SUPER_ADMIN))
			throw new IllegalOperationException("SuperAdmin Already present");
		// TODO Auto-generated method stub
			
		Admin admin = adminRepo.save(adminMapper.mapToAdmin(adminRequest,new Admin()));
		admin.setAdminType(AdminType.SUPER_ADMIN);
		admin=adminRepo.save(admin);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<AdminResponse>()
				.setStatusCode(HttpStatus.CREATED.value())
				.setMessage("User created")
				.setData(adminMapper.mapToAdminResponse(admin)));
	}

}
