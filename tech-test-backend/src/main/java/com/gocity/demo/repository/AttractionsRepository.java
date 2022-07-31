package com.gocity.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import org.springframework.data.domain.Pageable;


import com.gocity.demo.entity.Attractions;

@Repository
public interface  AttractionsRepository extends PagingAndSortingRepository<Attractions, String>{
	/***
	 * 
	 * @param destination
	 * @return
	 */
	@Query(value = "SELECT * FROM Attractions p WHERE LOWER(p.destination) = LOWER(:destination)", nativeQuery = true)
    List<Attractions> findByDestination(@Param("destination") String destination);
	
	/***
	 * 
	 * @param pageable
	 * @return
	 */
	@Query(value = "SELECT * FROM Attractions p ",
	        countQuery = "SELECT count(*) Attractions p ",
	        nativeQuery = true)
	Page<Attractions> findAllByName(Pageable pageable);
}
