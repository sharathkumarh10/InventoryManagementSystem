package com.jsp.warehousemanagement.requestdto;

import java.util.List;

import com.jsp.warehousemanagement.enums.MaterialType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StorageRequest {
	@NotNull(message = "Blockname cannot be null")
	@NotBlank(message = "Blockname cannot be blank")
	private String blockName;
	
	@NotNull(message = "Section cannot be null")
	@NotBlank(message = "Section"
			+ " cannot be blank")
	private String section;
	
	
	private List<MaterialType> materialTypes;

	

}
