package com.skipthedishes.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skipthedishes.domain.Cousine;
import com.skipthedishes.domain.Store;
import com.skipthedishes.service.CousineService;

/**
 * @author Vitor Carrilho - 18/03/2018
 *
 */
@RestController
@ControllerAdvice
@RequestMapping(value = { "/api/v1/cousine" })
public class CousineResource {
	
	private static final Logger logger = LoggerFactory.getLogger(CousineResource.class);
	
	private CousineService cousineService;
	
	@GetMapping
	public ResponseEntity<List<Cousine>> findAll() {
		logger.debug("get request to find all cousine made");
		List<Cousine> cousineList = cousineService.findAll();		
		return ResponseEntity.ok().body(cousineList);
	}
	
	@GetMapping("/search/{searchText}")
	public ResponseEntity<Cousine> findByName(@PathVariable String searchText) {
		logger.debug("get request to find cousine {} made", searchText);
		Cousine cousine = cousineService.findByName(searchText);
		return ResponseEntity.ok().body(cousine);
	}
	
	@GetMapping("/{id}/stores")
	public ResponseEntity<List<Store>> findAllStoresByCousineId(@PathVariable(name="id") long cousineId) {
		logger.debug("get request to find all stores related to cousine {} made", cousineId);
		List<Store> storeList = cousineService.findAllStoresByCousineId(cousineId);
		return ResponseEntity.ok().body(storeList);
	}
	
	@Autowired
	public CousineResource(CousineService cousineService) {
		this.cousineService = cousineService;
	}
}
