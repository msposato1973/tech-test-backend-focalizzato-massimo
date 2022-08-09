package com.gocity.demo.controller;

import java.util.Collection;
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
import com.gocity.demo.utility.ModelMapper;

abstract class SharedController {

private static final Logger LOGGER = LoggerFactory.getLogger(SharedController.class);
	
	@Value("${pagination.parameters.pageNumber}")
	public Integer pageNumber;
	
	@Value("${pagination.parameters.pageSize}")
	public Integer pageSize;
	
	@Value("${pagination.parameters.pageTotal}")
	public Integer total;


	 
	/***
	 * The a method to create an  setPageable sorted by input field name
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
	 * The a method to create an  ResponseEntity object as Response in case of PaginatedResponseAttraction for list of Destinations
	 * 
	 * @param PaginatedResponseAttraction paginatedResponse
	 * @return ResponseEntity 
	 */
	ResponseEntity<?> prepareResponseEntity(PaginatedResponseDestination paginatedResponse, List<Destinations> list) {
		LOGGER.info("prepareResponseEntity: begin");
		try {
		    List<com.gocity.demo.schema.Destinations> mappingListDTO = (List<com.gocity.demo.schema.Destinations>) mappingListDTO(list, "destinations");
			paginatedResponse = PaginatedResponseDestination.builder()
					 .pageSize(Integer.valueOf(pageSize))
					 .pageNumber(Integer.valueOf(pageNumber))
					 .total(total)
					 .results(mappingListDTO)
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
	 * The a method to create an  ResponseEntity object as Response in case of PaginatedResponseAttraction for list of Attraction
	 * 
	 * @param PaginatedResponseAttraction paginatedResponse
	 * @return ResponseEntity 
	 */
	ResponseEntity<?> prepareResponseEntity(PaginatedResponseAttraction paginatedResponse, List<Attractions> list) {
		LOGGER.info("prepareResponseEntity: begin");
		 try {
			 if(!list.isEmpty()) {
			    List<com.gocity.demo.schema.Attractions> mappingListDTO = (List<com.gocity.demo.schema.Attractions>) mappingListDTO(list, "attractions");
				paginatedResponse = PaginatedResponseAttraction.builder()
						.pageSize(Integer.valueOf(pageSize))
						.pageNumber(Integer.valueOf(pageNumber))
						.total(total)
						.results(mappingListDTO)
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
	 * The a method to create an  ResponseEntity object as Response in case of error of Attraction
	 * 
	 * @param Attractions response
	 * @return ResponseEntity
	 */
	ResponseEntity<?> prepareResponseEntity(Optional<Attractions> response, String UUID) {
		LOGGER.info("prepareResponseEntity - Attractions: begin");
		
		return errorResponseEntity(UUID, "404", "Attraction " );
		 
	}
	
	
	/***
	 * The a method to create an  ResponseEntity object as Response in case of error
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
	
	/***
	 * The a method to convert list of Entity object in DTO  attractions or destinations objects
	 * @param listEntity
	 * @param nameType
	 * @return
	 */
	Collection<?> mappingListDTO(List<?> listEntity, String nameType) {
	 
		if (nameType.equalsIgnoreCase("attractions")) {
			return (List<com.gocity.demo.schema.Attractions>) ModelMapper.convertListEntityToDto(listEntity, "attractions");
		} else	{	
			return (List<com.gocity.demo.schema.Destinations>) ModelMapper.convertListEntityToDto(listEntity, "destinations");
		}
	}
	
	/***
	 * The a method to convert Entity object in DTO  Destination object: 
	 * @param antity
	 * @return
	 */
	protected com.gocity.demo.schema.Destinations convertEntityToDTO(com.gocity.demo.entity.Destinations antity) {
		 
		return ModelMapper.convertToDestinationsDto(antity);
	}
	
	/***
	 * The a method to convert Entity object in DTO  Attractions object
	 * 
	 * @param antity
	 * @return
	 */
	protected com.gocity.demo.schema.Attractions  convertEntityToDTO(com.gocity.demo.entity.Attractions antity) {
		 
		return ModelMapper.convertToAttractionsDto(antity);
	}
	
	
}
