package com.jsp.warehousemanagement.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jsp.warehousemanagement.entity.Admin;
import com.jsp.warehousemanagement.entity.WareHouse;
import com.jsp.warehousemanagement.enums.AdminType;
import com.jsp.warehousemanagement.enums.Privilege;
import com.jsp.warehousemanagement.exception.AdminNotFindByEmailException;
import com.jsp.warehousemanagement.exception.AdminNotFoundByIdException;
import com.jsp.warehousemanagement.exception.IllegalOperationException;
import com.jsp.warehousemanagement.exception.WarehouseNotFoundByIdException;
import com.jsp.warehousemanagement.mapper.AdminMapper;
import com.jsp.warehousemanagement.repository.AdminRepository;
import com.jsp.warehousemanagement.repository.WareHouseRepository;
import com.jsp.warehousemanagement.requestdto.AdminRequest;
import com.jsp.warehousemanagement.responsedto.AdminResponse;
import com.jsp.warehousemanagement.service.AdminService;
import com.jsp.warehousemanagement.serviceimpl.AdminServiceImpl.AdminNotFindByIdException;
import com.jsp.warehousemanagement.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class AdminServiceImpl implements AdminService {

	public class AdminNotFindByIdException {

	}

	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private WareHouseRepository warehouseRepo;

	@Autowired
	private AdminMapper adminMapper;

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(AdminRequest adminRequest) {
		if (adminRepo.existsByAdminType(AdminType.SUPER_ADMIN))
			throw new IllegalOperationException("SuperAdmin Already present");
		// TODO Auto-generated method stub

		Admin admin = adminRepo.save(adminMapper.mapToAdmin(adminRequest, new Admin()));
		admin.setAdminType(AdminType.SUPER_ADMIN);
		admin = adminRepo.save(admin);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<AdminResponse>().setStatusCode(HttpStatus.CREATED.value())
						.setMessage("User created").setData(adminMapper.mapToAdminResponse(admin)));
	}

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(@Valid AdminRequest adminRequest,
			int warehouseId) {
		// TODO Auto-generated method stub
//		Optional<WareHouse> owh = warehouseRepo.findById(warehouseId);
//		if (owh.isEmpty())
//			throw new WarehouseNotFoundByIdException("WareHouse not present");
//		// TODO Auto-generated method stub
//
//		Admin admin = adminRepo.save(adminMapper.mapToAdmin(adminRequest, new Admin()));
//		admin.setAdminType(AdminType.ADMIN);
//		admin = adminRepo.save(admin);

		return warehouseRepo.findById(warehouseId).map(wareHouse -> {
			Admin admin = adminMapper.mapToAdmin(adminRequest, new Admin());
			admin.setAdminType(AdminType.ADMIN);
			adminRepo.save(admin);

			wareHouse.setAdmin(admin);

			warehouseRepo.save(wareHouse);

			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseStructure<AdminResponse>().setStatusCode(HttpStatus.CREATED.value())
							.setMessage("Admin created").setData(adminMapper.mapToAdminResponse(admin)));

		}).orElseThrow(() -> new WarehouseNotFoundByIdException("warehouse not found"));

	}

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(@Valid AdminRequest adminRequest) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		return adminRepo.findByEmail(email).map(exAdmin -> {
			Admin admin = adminMapper.mapToAdmin(adminRequest, exAdmin);

			Admin updatedAdmin = adminRepo.save(admin);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<AdminResponse>().setStatusCode(HttpStatus.OK.value())
							.setMessage("Admin Updated").setData(adminMapper.mapToAdminResponse(updatedAdmin)));
		}).orElseThrow(() -> new AdminNotFindByEmailException("Admin not found by email"));

	}

	// TODO Auto-generated method stub
	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdminBySuperAdmin(AdminRequest adminRequest,
			int adminId) {
		return adminRepo.findById(adminId).map(exadmin -> {
			adminMapper.mapToAdmin(adminRequest, exadmin);
			exadmin = adminRepo.save(exadmin);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<AdminResponse>().setStatusCode(HttpStatus.OK.value())
							.setMessage("Admin Updated").setData(adminMapper.mapToAdminResponse(exadmin)));
		}).orElseThrow(() -> new AdminNotFoundByIdException("Admin not Found"));
	}
	
	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> findAdminById(int adminId) {
		
	return adminRepo.findById(adminId).map(admin->{
			
			return ResponseEntity.status(HttpStatus.FOUND)
					.body(new ResponseStructure<AdminResponse>()
							.setStatusCode(HttpStatus.FOUND.value())
							.setMessage("Admin Found")
							.setData(adminMapper.mapToAdminResponse(admin)));
		}).orElseThrow(()-> new AdminNotFoundByIdException("Admin not found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<AdminResponse>>> findAllAdmins() {
		List<AdminResponse> adminsList = adminRepo.findAll().stream().map(admin -> 
			adminMapper.mapToAdminResponse(admin)).toList();
		
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<List<AdminResponse>>()
						.setStatusCode(HttpStatus.FOUND.value())
						.setMessage("Admins Found")
						.setData(adminsList));
	}

}
