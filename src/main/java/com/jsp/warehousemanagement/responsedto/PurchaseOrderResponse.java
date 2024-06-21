package com.jsp.warehousemanagement.responsedto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class PurchaseOrderResponse {
	
	private int orderId;
	private int orderQuantity;
	private String invoiceLink;
	private int customerId;
	private String status;
}
