package com.jsp.warehousemanagement.responsedto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@Builder
public class ClientResponse {

	private String apiKey;
	private String message;

}
