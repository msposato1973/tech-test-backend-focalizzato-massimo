package com.gocity.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gocity.demo.entity.Destinations;
import com.gocity.demo.repository.DestinationRepository;

@Service
/***
 * 
 * @author maxp7
 *
 */
public class DestinationService implements IDestinationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DestinationService.class);
	
	
	private final  DestinationRepository repository;

	/***
	 * Constructor
	 * @param destinationRepository
	 */
	public DestinationService(DestinationRepository destinationRepository) {
		this.repository = destinationRepository;
	}


	@Override
	public List<Destinations> findPaginated(int pageNo, int pageSize) {
		 Pageable paging = PageRequest.of(pageNo, pageSize);
		 Page<Destinations> pagedResult = repository.findAll(paging);
	 
		 return pagedResult.toList();
	}


	@Override
	public List<Destinations> findAll() {
		List<Destinations> result = new ArrayList<Destinations>();
		repository.findAll().forEach(result::add);
	    
		return  result;
	}


	@Override
	public Destinations addDestination(Destinations destinations) {
		LOGGER.info("addDestination: begin");
		return repository.save(destinations);
	}


	@Override
	public Optional<Destinations> getDestinationById(String id) {
		LOGGER.info("getDestinationById: begin");
		return repository.findById(id);
	}
}
