package com.gocity.demo.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gocity.demo.WebParams;
import com.gocity.demo.entity.Destinations;
import com.gocity.demo.exception.CustomNotFoundException;
import com.gocity.demo.schema.PaginatedResponseDestination;
import com.gocity.demo.service.DestinationService;
import com.gocity.demo.service.IDestinationService;

@RestController
@RequestMapping(WebParams.API)
public class DestinationController extends SharedController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DestinationController.class);
	
	private final IDestinationService destinationService;
	
	/***
	 * Constructor
	 * @param DestinationService destinationService
	 */
	public DestinationController(DestinationService destinationService){
			this.destinationService = destinationService;
	}
	
	@GetMapping(WebParams.DESTINATION_API)
	public ResponseEntity<?> findAll(
			@RequestParam(required = false, value = "page", defaultValue = "0") Integer page,
		    @RequestParam(required = false, value = "size", defaultValue = "10") Integer size
	) {
		LOGGER.info("findAll: begin");
		return prepareResponseEntity(new PaginatedResponseDestination(), destinationService.findAll());
	}
	
	
	/***
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(WebParams.DESTINATION_ID_API)
	public ResponseEntity<?> findById(@PathVariable("id") String id) {
		LOGGER.info("findById: begin");
		Optional<Destinations> destination = destinationService.getDestinationById(id);
		
		if(!destination.isPresent()) throw new CustomNotFoundException(id.toString()); 
		
		return  new ResponseEntity<>(destination, HttpStatus.OK);  
	}

	/***
	 * 
	 * @param destinations
	 * @return
	 * @throws Exception
	 */
	@PostMapping(path = WebParams.DESTINATION_ADD)
	public ResponseEntity<?> create(@RequestBody Destinations destinations) throws Exception 
	{ 
		  
		Destinations antity = destinationService.addDestination(destinations);
		return new ResponseEntity<>( antity, HttpStatus.CREATED);   
	}
	
	
}
