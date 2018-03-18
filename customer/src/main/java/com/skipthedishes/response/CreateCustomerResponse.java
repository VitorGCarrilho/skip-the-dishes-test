package com.skipthedishes.response;

/**
 * @author Vitor Carrilho - 18/03/2018
 *
 */
public class CreateCustomerResponse {
	private long id;

	public long getId() {
		return id;
	}
	
	public static CreateCustomerResponse ofId(long id){
		CreateCustomerResponse response = new CreateCustomerResponse();
		response.id = id;
		return response;
	}
}
