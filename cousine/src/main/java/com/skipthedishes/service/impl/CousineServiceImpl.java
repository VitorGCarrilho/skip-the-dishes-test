package com.skipthedishes.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skipthedishes.domain.Cousine;
import com.skipthedishes.domain.Store;
import com.skipthedishes.exception.NotFoundException;
import com.skipthedishes.repository.CousineRepository;
import com.skipthedishes.service.CousineService;

/**
 * @author Vitor Carrilho - 18/03/2018
 *
 */
@Service
public class CousineServiceImpl implements CousineService {

	private static final Logger logger = LoggerFactory.getLogger(CousineServiceImpl.class);

	private CousineRepository cousineRepository;

	/*
	 * @see
	 * com.skipthedishes.service.CousineService#findByName(java.lang.String)
	 */
	@Override
	public Cousine findByName(String name) {
		Optional<Cousine> optCousine = cousineRepository.findByName(name);
		logger.debug("searching for {} cousine", name);
		return optCousine.orElseThrow(() -> {
			logger.debug("Cousine {} not found", name);
			return new NotFoundException("The cousine wasn't found");
		});
	}

	/*
	 * @see com.skipthedishes.service.CousineService#findAll()
	 */
	@Override
	public List<Cousine> findAll() {
		List<Cousine> cousineList = cousineRepository.findAll();
		logger.debug("Searching for all cousines");
		return cousineList;
	}

	/*
	 * @see com.skipthedishes.service.CousineService#findAllStores(long)
	 */
	@Override
	public List<Store> findAllStoresByCousineId(long id) {
		Optional<Cousine> optCousine = cousineRepository.findById(id);
		logger.debug("Searching stores related to cousine "+id);
		Cousine cousine = optCousine.orElseThrow(() -> {
			logger.debug("Cousine {} not found", id);
			return new NotFoundException("The cousine wasn't found");
		});
		return cousine.getStoreList();
	}

	@Autowired
	public CousineServiceImpl(CousineRepository cousineRepository) {
		this.cousineRepository = cousineRepository;
	}
}
