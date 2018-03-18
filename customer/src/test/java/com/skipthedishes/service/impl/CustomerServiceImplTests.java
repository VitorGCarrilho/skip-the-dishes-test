package com.skipthedishes.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.skipthedishes.domain.Customer;
import com.skipthedishes.exception.EmailAlreadyExists;
import com.skipthedishes.repository.CustomerRepository;

/**
 * @author Vitor Carrilho - 18/03/2018
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTests {
	
	/**
	 * 
	 */
	private static final String JUNIT_PASSWORD = "junitPassword";

	/**
	 * 
	 */
	private static final String ENCODED_JUNIT_PASSWORD = "EncodedJunitPassword";

	/**
	 * 
	 */
	private static final int CUSTOMER_ID = 100;

	private CustomerServiceImpl customerService;
	
	@Mock
	private CustomerRepository customerRepository;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	private Customer customer;
	
	@Test
	public void saveUserTest() {
		when(customerRepository.save(customer)).thenReturn(customer);
		when(passwordEncoder.encode(customer.getPassword())).thenReturn(ENCODED_JUNIT_PASSWORD);
		long customerId = customerService.save(customer);
		assertEquals(CUSTOMER_ID, customerId);
		assertEquals(ENCODED_JUNIT_PASSWORD, customer.getPassword());
		verify(customerRepository, times(1)).save(customer);
		verify(passwordEncoder, times(1)).encode(JUNIT_PASSWORD);
	}
	
	@Test(expected=EmailAlreadyExists.class)
	public void saveUserEmailAlreadyExistTest() {
		when(customerRepository.save(customer)).thenThrow(new DataIntegrityViolationException("email already exists"));
		when(passwordEncoder.encode(customer.getPassword())).thenReturn(ENCODED_JUNIT_PASSWORD);
		long customerId = customerService.save(customer);
		assertEquals(CUSTOMER_ID, customerId);
		assertEquals(ENCODED_JUNIT_PASSWORD, customer.getPassword());
		verify(customerRepository, times(1)).save(customer);
		verify(passwordEncoder, times(1)).encode(JUNIT_PASSWORD);
	}
	
	@Before
	public void setup() {
		customerService = new CustomerServiceImpl(customerRepository, passwordEncoder);
		customer = new Customer();
		customer.setId(CUSTOMER_ID);
		customer.setEmail("vitorg.carrilho@gmail.com");
		customer.setName("Vitor Gabriel Carrilho");
		customer.setPassword(JUNIT_PASSWORD);
	}
	
	

}
