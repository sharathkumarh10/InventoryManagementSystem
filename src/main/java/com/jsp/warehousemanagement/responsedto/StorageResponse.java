package com.jsp.warehousemanagement.responsedto;

import java.util.List;

import com.jsp.warehousemanagement.enums.MaterialType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class StorageResponse {
	
	private int storageId;
	private String blockName;
	private String section;
	private double capacityInKg;
	
	
	List<MaterialType> materialTypes;
	private double maxAdditionalWeight;
	private double availableAreaInMetre;

}
