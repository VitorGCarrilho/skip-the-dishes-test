package com.skipthedishes.resource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.skipthedishes.domain.Customer;
import com.skipthedishes.exception.EmailAlreadyExists;
import com.skipthedishes.response.CreateCustomerResponse;
import com.skipthedishes.service.CustomerService;

/**
 * @author Vitor Carrilho - 18/03/2018
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerResourceTest {
	
	/**
	 * 
	 */
	private static final long CUSTOMER_ID = 100;

	private CustomerResource customerResource;
	
	@Mock
	private CustomerService customerService;
	
	@Mock
	private Customer customer;
	
	@Test
	public void saveCustomerTest() {
		when(customerService.save(customer)).thenReturn(CUSTOMER_ID);
		ResponseEntity<CreateCustomerResponse> responseEntity = customerResource.createCustomer(customer);
		assertEquals(CUSTOMER_ID, responseEntity.getBody().getId());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		verify(customerService, times(1)).save(customer);
	}
	
	@Test(expected=EmailAlreadyExists.class)
	public void saveCustomerEmailAlreadyExistTest() {
		when(customerService.save(customer)).thenThrow(new EmailAlreadyExists("Email already exists"));
		ResponseEntity<CreateCustomerResponse> responseEntity = customerResource.createCustomer(customer);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		verify(customerService, times(1)).save(customer);
	}
	
	@Before
	public void setup(){
		customerResource = new CustomerResource(customerService);
	}
}
