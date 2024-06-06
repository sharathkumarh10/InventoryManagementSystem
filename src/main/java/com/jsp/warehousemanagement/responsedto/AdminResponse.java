package com.jsp.warehousemanagement.responsedto;

import java.util.List;

import com.jsp.warehousemanagement.enums.AdminType;
import com.jsp.warehousemanagement.enums.Privilege;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@Builder
public class AdminResponse {
	
	private int adminId;
	private String name;
	private String email;
	private AdminType adminType;
	private List<Privilege> privileges;

}
