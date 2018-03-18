package com.skipthedishes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skipthedishes.domain.Customer;

/**
 * @author Vitor Carrilho - 18/03/2018
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Optional<Customer> findByName(String name);
	
	Optional<Customer> findById(long id);
}
