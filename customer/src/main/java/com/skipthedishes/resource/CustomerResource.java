package com.skipthedishes.resource;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skipthedishes.domain.Customer;
import com.skipthedishes.response.CreateCustomerResponse;
import com.skipthedishes.service.CustomerService;

/**
 * @author Vitor Carrilho - 18/03/2018
 *
 */
@RestController
@ControllerAdvice
@RequestMapping(value = { "/api/v1/customer" })
public class CustomerResource {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerResource.class);
	
	private CustomerService customerService;
	
	@PostMapping
	public ResponseEntity<CreateCustomerResponse> createCustomer(@RequestBody @Valid Customer customer) {
		logger.debug("Request made to save customer {}", customer.getEmail());
		long createdID = customerService.save(customer);
		return ResponseEntity.ok().body(CreateCustomerResponse.ofId(createdID));
	}	
	
	@Autowired
	public CustomerResource(CustomerService customerService) {
		this.customerService = customerService;
	}
}
