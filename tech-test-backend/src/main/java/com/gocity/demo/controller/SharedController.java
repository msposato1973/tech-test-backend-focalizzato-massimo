package com.gocity.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gocity.demo.entity.Attractions;
import com.gocity.demo.entity.Destinations;
import com.gocity.demo.exception.ExceptionsTemplate;
import com.gocity.demo.schema.ErrorResponse;
import com.gocity.demo.schema.PaginatedResponseAttraction;
import com.gocity.demo.schema.PaginatedResponseDestination;

public class SharedController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SharedController.class);
	
	@Value("${parameters.pageNumber}")
	public Integer pageNumber;
	
	@Value("${parameters.pageSize}")
	public Integer pageSize;
	
	@Value("${parameters.pageTotal}")
	public Integer total;


	 
	/***
	 * 
	 * @param sortBy
	 * @return
	 */
	protected Pageable setPageable(String sortBy) {
		LOGGER.info("prepareResponseEntity: begin");
		LOGGER.info(" prepareResponseEntity - set pagination by param : " + sortBy);
		Pageable pageable = PageRequest.of(0, 10,   Sort.by(sortBy).descending() );
		
		return pageable;
	}
	 
	/***
	 * 
	 * @param PaginatedResponseAttraction paginatedResponse
	 * @return ResponseEntity 
	 */
	ResponseEntity<?> prepareResponseEntity(PaginatedResponseDestination paginatedResponse, List<Destinations> list) {
		LOGGER.info("prepareResponseEntity: begin");
		try {
			 paginatedResponse = PaginatedResponseDestination.builder()
					 .pageSize(Integer.valueOf(pageSize))
					 .pageNumber(Integer.valueOf(pageNumber))
					 .total(total)
					 .results(list)
					 .build();
			 
			LOGGER.info("prepareResponseEntity: end");
			return new ResponseEntity<>(paginatedResponse, HttpStatus.OK);
			
		} catch (Exception e) {
			 
			 return new ResponseEntity<>(
					 new ErrorResponse("403", ExceptionsTemplate.FORBIDDEN), HttpStatus.FORBIDDEN
			 );
		
		}
		 
	}
	
	/***
	 * 
	 * @param PaginatedResponseAttraction paginatedResponse
	 * @return ResponseEntity 
	 */
	ResponseEntity<?> prepareResponseEntity(PaginatedResponseAttraction paginatedResponse, List<Attractions> list) {
		LOGGER.info("prepareResponseEntity: begin");
		 try {
			 if(!list.isEmpty()) {
				 paginatedResponse = PaginatedResponseAttraction.builder()
						.pageSize(Integer.valueOf(pageSize))
						.pageNumber(Integer.valueOf(pageNumber))
						.total(total)
						.results(list)
						.build();
				 
				LOGGER.info("!list.isEmpty()");
				return new ResponseEntity<>(paginatedResponse, HttpStatus.OK);
			} else {
				LOGGER.info("list.isEmpty()");
				return new ResponseEntity<>( new ErrorResponse("204","No Content"), HttpStatus.OK);
			}
		 } catch (Exception e) {
			 
			 return new ResponseEntity<>(
					 new ErrorResponse("403", ExceptionsTemplate.FORBIDDEN), HttpStatus.FORBIDDEN
			 );
			
		}
	}
	
	 
	
	/***
	 * 
	 * @param Attractions response
	 * @return ResponseEntity
	 */
	ResponseEntity<?> prepareResponseEntity(Optional<Attractions> response, String UUID) {
		LOGGER.info("prepareResponseEntity - Attractions: begin");
		
		return   errorResponseEntity(UUID, "404", "Attraction " );
		 
	}
	
	
	/***
	 * 
	 * @param Destinations response
	 * @return ResponseEntity
	 */
	ResponseEntity<?> errorResponseEntity(String UUID, String codeError, String entityName) {
		LOGGER.info("errorResponseEntity: begin");
		ErrorResponse errorResponse = null;
		
		if (codeError.equals("404")) {
		 	errorResponse = new ErrorResponse(codeError, ExceptionsTemplate.NOT_FOUND.concat(UUID));
		 	return new ResponseEntity<>(errorResponse , HttpStatus.FOUND);
		} else if(codeError.equals("403")){
			errorResponse = new ErrorResponse(codeError, ExceptionsTemplate.FORBIDDEN);
			return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	
}
