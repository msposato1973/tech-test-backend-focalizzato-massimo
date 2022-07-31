package com.gocity.demo.service;

import java.util.List;
import java.util.Optional;

import com.gocity.demo.entity.Destinations;

/**
 * 
 * @author maxp7
 *
 */
public interface IDestinationService {
	/***
	 * 
	 * @param int pageNo
	 * @param int pageSize
	 * @return List<Destinations>
	 */
	List<Destinations> findPaginated(int pageNo, int pageSize);
	
	/***
	 * 
	 * @param String id
	 * @return Optional<Destinations>
	 */
	Optional<Destinations>  getDestinationById(String id);
	
	/***
	 * 
	 * @return List<Destinations
	 */
	List<Destinations> findAll() ;
	
	/***
	 * 
	 * @param destinations
	 * @return Destinations
	 */
	Destinations addDestination(Destinations destinations);
}
