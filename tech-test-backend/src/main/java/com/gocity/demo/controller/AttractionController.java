package com.gocity.demo.controller;

import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gocity.demo.WebParams;
import com.gocity.demo.entity.Attractions;
import com.gocity.demo.exception.CustomForbiddenException;
import com.gocity.demo.exception.CustomNotFoundException;
import com.gocity.demo.exception.ExceptionsTemplate;
import com.gocity.demo.schema.ErrorResponse;
import com.gocity.demo.schema.PaginatedResponseAttraction;
import com.gocity.demo.service.AttractionsService;
import com.gocity.demo.service.IAttractionsService;


@RestController
@RequestMapping(WebParams.API)
public class AttractionController extends SharedController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AttractionController.class);
	

	private final IAttractionsService attractionsService;
	
	/***
	 * Constructor
	 * @param AttractionsService attractionsService
	 */
	public AttractionController(AttractionsService attractionsService){
			this.attractionsService = attractionsService;
	}

	/***
	 * 
	 * @param destinationId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@GetMapping(WebParams.ATTRACTION_API)
	public ResponseEntity<?> findId(@RequestParam String destinationId,
			@RequestParam(required = false, value = "pageNumber", defaultValue = "0") Integer pageNumber,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") Integer pageSize) {

		LOGGER.info("findAll: begin");
		if ((destinationId.equals("") && destinationId == null)) extractedException(destinationId);
		 

		Optional<Attractions> list = attractionsService.findByDestinationsId(destinationId);
		if (list.isEmpty()) {
			throw new CustomForbiddenException(ExceptionsTemplate.FORBIDDEN + destinationId);
		}

		return prepareResponseEntity(new PaginatedResponseAttraction(), list.stream().collect(Collectors.toList()));
	}

	private void extractedException(String destinationId) {
		throw new CustomNotFoundException(ExceptionsTemplate.NOT_FOUND + destinationId);
	}
	
	/***
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param sortBy
	 * @return
	 */
	@GetMapping(WebParams.ATTRACTION_PAGINATION)
	public ResponseEntity<?> findAllByPagination(
			@RequestParam(required = false, value = "pageNumber", defaultValue = "0") Integer pageNumber,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(required = false, value = "sortby", defaultValue = "name") String sortBy) {

		LOGGER.info("findAllByPagination: begin");
	
		Page<Attractions> attractionsPage;
		try {
			
			attractionsPage = attractionsService.findAllByName(
					setPageable(sortBy)
			);
			
			if (attractionsPage.isEmpty()) {
				throw new CustomForbiddenException(ExceptionsTemplate.FORBIDDEN);
			}
			
			return prepareResponseEntity(new PaginatedResponseAttraction(),
					attractionsPage.stream().collect(Collectors.toList()));
			
		} catch (Exception e) {
			
			  return new ResponseEntity<>(
					 new ErrorResponse("500", ExceptionsTemplate.BED_REQUEST), HttpStatus.BAD_REQUEST
			 );
		}

		
	}

	/***
	 * 
	 * @param attractions
	 * @return
	 * @throws Exception
	 */
	@PostMapping(path = WebParams.ATTRACTION_ADD)
	public ResponseEntity<Object> create(@RequestBody Attractions attractions) throws Exception {

		Attractions antity = attractionsService.addAttraction(attractions);
		return new ResponseEntity<>(antity, HttpStatus.CREATED);
	}

}
