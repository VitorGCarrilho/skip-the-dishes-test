package com.skipthedishes.service;

import java.util.List;

import com.skipthedishes.domain.Cousine;
import com.skipthedishes.domain.Store;

/**
 * @author Vitor Carrilho - 18/03/2018
 *
 */
public interface CousineService {
	
	/**
	 * Method that finds a given cousine by the name
	 * @param name
	 * **/
	Cousine findByName(String name);

	/**
	 * Method that finds all cousine
	 * @return
	 */
	List<Cousine> findAll();

	/**
	 * Method that finds all stores related to a Cousine
	 * @param id
	 * @return
	 */
	List<Store> findAllStoresByCousineId(long id);
}
