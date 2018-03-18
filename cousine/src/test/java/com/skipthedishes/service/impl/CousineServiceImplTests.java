package com.skipthedishes.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.skipthedishes.domain.Cousine;
import com.skipthedishes.domain.Store;
import com.skipthedishes.exception.NotFoundException;
import com.skipthedishes.repository.CousineRepository;

/**
 * @author Vitor Carrilho - 18/03/2018
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CousineServiceImplTests {
	
	/**
	 * 
	 */
	private static final int COUSINE_ID = 100;

	private static final String NAME = "NAME";

	@Mock
	private CousineRepository cousineRepository;
	
	@Mock
	private Cousine cousine;
	
	@Mock
	private Store store;
	
	private CousineServiceImpl cousineService;

	@Test
	public void findCousineTest() {
		when(cousineRepository.findByName(NAME)).thenReturn(Optional.of(cousine));		
		Cousine newCousine = cousineService.findByName(NAME);
		verify(cousineRepository, times(1)).findByName(NAME);
		assertEquals(cousine, newCousine);
	}
	
	@Test(expected=NotFoundException.class)
	public void noCousineFoundTest() {
		when(cousineRepository.findByName(NAME)).thenReturn(Optional.empty());
		cousineService.findByName(NAME);
		verify(cousineRepository, times(1)).findByName(NAME);
	}
	
	@Test
	public void findAllTest() {
		when(cousineRepository.findAll()).thenReturn(Arrays.asList(cousine));
		List<Cousine> cousineList = cousineService.findAll();
		assertEquals(cousine, cousineList.get(0));
		verify(cousineRepository, times(1)).findAll();
	}
	
	@Test
	public void findStoreList() {
		when(cousineRepository.findById(COUSINE_ID)).thenReturn(Optional.of(cousine));
		when(cousine.getStoreList()).thenReturn(Arrays.asList(store));
		List<Store> newStoreList = cousineService.findAllStoresByCousineId(COUSINE_ID);
		assertEquals(store, newStoreList.get(0));
		verify(cousineRepository, times(1)).findById(COUSINE_ID);
		verify(cousine, times(1)).getStoreList();
	}
	
	@Test(expected=NotFoundException.class)
	public void findStoreListInvalidId() {
		when(cousineRepository.findById(COUSINE_ID)).thenReturn(Optional.empty());
		cousineService.findAllStoresByCousineId(COUSINE_ID);
		verify(cousineRepository, times(1)).findById(COUSINE_ID);
	}
	
	@Before
	public void setup() {
		cousineService = new CousineServiceImpl(cousineRepository);
	}

}
