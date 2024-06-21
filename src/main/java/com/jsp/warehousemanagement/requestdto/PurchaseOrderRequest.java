package com.jsp.warehousemanagement.requestdto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PurchaseOrderRequest {
	
	private int orderQuantity;
	private String invoiceLink;
	private int customerId;

}
