package com.skipthedishes.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skipthedishes.domain.Customer;
import com.skipthedishes.exception.EmailAlreadyExists;
import com.skipthedishes.repository.CustomerRepository;
import com.skipthedishes.service.CustomerService;


/**
 * @author Vitor Carrilho - 18/03/2018
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	private CustomerRepository customerRepository;
	
	private PasswordEncoder passwordEncoder;

	/*
	 * @see com.skipthedishes.service.CustomerService#save(com.skipthedishes.domain.Customer)
	 */
	@Override
	public long save(Customer customer) {
		logger.error("saving customer {}", customer.getEmail());
		String encodedPassword = passwordEncoder.encode(customer.getPassword());
		customer.setPassword(encodedPassword);
		try {
			customerRepository.save(customer);
			logger.error("customer {} saved with id {}", customer.getEmail(), customer.getId());
		} catch (DataIntegrityViolationException e) {
			logger.error("email {} already exists", customer.getEmail());
			throw new EmailAlreadyExists("email already exists");
		}
		return customer.getId();
	}

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
		this.customerRepository = customerRepository;
		this.passwordEncoder = passwordEncoder;
	}
}
