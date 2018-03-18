package com.skipthedishes.resource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.skipthedishes.domain.Cousine;
import com.skipthedishes.domain.Store;
import com.skipthedishes.exception.NotFoundException;
import com.skipthedishes.service.CousineService;

/**
 * @author Vitor Carrilho - 18/03/2018
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CousineResourceTests {

	/**
	 * 
	 */
	private static final int COUSINE_ID = 100;

	private static final String NAME = "NAME";

	private CousineResource cousineResource;
	
	@Mock
	private CousineService cousineService;
	
	@Mock
	private Cousine cousine;
	
	@Mock
	private Store store;

	@Test
	public void findByNameTest() {
		when(cousineService.findByName(NAME)).thenReturn(cousine);
		ResponseEntity<Cousine> cousineResponse = cousineResource.findByName(NAME);
		Cousine newCousine = cousineResponse.getBody();
		HttpStatus httpStatus = cousineResponse.getStatusCode();
		assertEquals(cousine, newCousine);
		assertEquals(HttpStatus.OK, httpStatus);
		verify(cousineService, times(1)).findByName(NAME);
	}
	
	@Test(expected=NotFoundException.class)
	public void findByNameNotFoundTest() {
		when(cousineService.findByName(NAME)).thenThrow(new NotFoundException());
		ResponseEntity<Cousine> cousineResponse = cousineResource.findByName(NAME);
		HttpStatus httpStatus = cousineResponse.getStatusCode();
		assertEquals(HttpStatus.NOT_FOUND, httpStatus);
		verify(cousineService, times(1)).findByName(NAME);
	}
	
	@Test
	public void findAll() {
		when(cousineService.findAll()).thenReturn(Arrays.asList(cousine));
		ResponseEntity<List<Cousine>> response = cousineResource.findAll();
		List<Cousine> cousineList = response.getBody();
		HttpStatus httpStatus = response.getStatusCode();
		assertEquals(cousine, cousineList.get(0));
		assertEquals(HttpStatus.OK, httpStatus);
		verify(cousineService, times(1)).findAll();
	}
	
	@Test
	public void findAllStores(){
		when(cousineService.findAllStoresByCousineId(COUSINE_ID)).thenReturn(Arrays.asList(store));
		ResponseEntity<List<Store>> response = cousineResource.findAllStoresByCousineId(COUSINE_ID);
		List<Store> storeList = response.getBody();
		HttpStatus httpStatus = response.getStatusCode();
		assertEquals(store, storeList.get(0));
		assertEquals(HttpStatus.OK, httpStatus);
		verify(cousineService, times(1)).findAllStoresByCousineId(COUSINE_ID);
	}
	
	@Test(expected=NotFoundException.class)
	public void findAllStoresCousineIdNotFound(){
		when(cousineService.findAllStoresByCousineId(COUSINE_ID)).thenThrow(new NotFoundException());
		ResponseEntity<List<Store>> response = cousineResource.findAllStoresByCousineId(COUSINE_ID);
		HttpStatus httpStatus = response.getStatusCode();
		assertEquals(HttpStatus.NOT_FOUND, httpStatus);
		verify(cousineService, times(1)).findAllStoresByCousineId(COUSINE_ID);
	}
	
	@Before
	public void setup() {
		cousineResource = new CousineResource(cousineService);
	}
	

}
