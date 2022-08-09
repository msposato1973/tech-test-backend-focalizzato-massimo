package com.gocity.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gocity.demo.entity.Attractions;
import com.gocity.demo.repository.AttractionRepository;
import com.gocity.demo.service.IAttractionService;

@Service
public class AttractionsService implements IAttractionService {


private static final Logger LOGGER = LoggerFactory.getLogger(AttractionsService.class);
	
	private  AttractionRepository repository;
	
	/***
	 * Constructor
	 * @param attractionsRepository
	 */
	public AttractionsService(AttractionRepository attractionsRepository) {
		this.repository = attractionsRepository;
	}
	
	 
	@Override
	public List<Attractions> findPaginated(int pageNo, int pageSize) {
		LOGGER.info("findPaginated: begin");
		Pageable paging = PageRequest.of(pageNo, pageSize);
	    Page<Attractions> pagedResult = repository.findAll(paging);
	 
		return pagedResult.toList();
	}

	public Optional<Attractions> findById(String id) {
		LOGGER.info("findById: begin");
		return repository.findById(id);
	}

	@Override
	public List<Attractions> findAll() {
		LOGGER.info("findAll: begin");
		List<Attractions> result = new ArrayList<Attractions>();
		repository.findAll().forEach(result::add);
	    
		return  result;
	}

	@Override
	public Attractions addAttraction(Attractions attractions) {
		LOGGER.info("addAttraction: begin");
		return repository.save(attractions);
	}

	@Override
	public List<Attractions> findByDestinationsId(String destinationsId) {
		// TODO Auto-generated method stub
		List<Attractions> list = repository.findByDestination(destinationsId);
		return list;
	}


	@Override
	public Page<Attractions> findAllByName(Pageable pageable) {
		LOGGER.info("findAllByName name, pageable: begin");
		Page<Attractions> personPage = repository.findAllByName(pageable);
		return personPage;
	}

}
