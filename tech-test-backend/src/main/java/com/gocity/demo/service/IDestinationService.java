package com.gocity.demo.service;


import java.util.List;
import java.util.Optional;

import com.gocity.demo.entity.Destinations;

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
	
	com.gocity.demo.entity.Destinations addDestination(com.gocity.demo.entity.Destinations destinations);
}
