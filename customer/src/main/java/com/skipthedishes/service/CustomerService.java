package com.skipthedishes.service;

import com.skipthedishes.domain.Customer;
/**
 * @author Vitor Carrilho - 18/03/2018
 *
 */
public interface CustomerService {
	
	/**
	 * Method that saves a customer
	 * @param name
	 * **/
	long save(Customer customer);
}
