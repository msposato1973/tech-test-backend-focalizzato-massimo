package com.gocity.demo.service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gocity.demo.entity.Attractions;

public interface IAttractionService {

	/***
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Attractions> findPaginated(int pageNo, int pageSize);
	
	/***
	 * 
	 * @param id
	 * @return
	 */
	Optional<Attractions>  findById(String id);
	/***
	 * 
	 * @return
	 */
	
	List<Attractions> findAll() ;
	/***
	 * 
	 * @param attractions
	 * @return
	 */
	Attractions addAttraction(Attractions attractions);
	
	/***
	 * 
	 * @param destinationsId
	 * @return
	 */
	List<Attractions>  findByDestinationsId(String destinationsId);
	
	/***
	 * 
	 * @param pageable
	 * @return
	 */
	Page<Attractions> findAllByName(Pageable pageable);

	
}
