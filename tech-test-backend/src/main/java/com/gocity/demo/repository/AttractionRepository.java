package com.gocity.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gocity.demo.entity.Attractions;

/**
 * Created by STS Eclipse.
 * Project : spring-boot-one-to-many-example
 * User: msposato1973
 * Email: maxp73@gmail.com
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface AttractionRepository extends PagingAndSortingRepository<Attractions, String> {
	
	/***
	 * 
	 * @param destination
	 * @return
	 */ 
	@Query(value = "SELECT a.* FROM Attractions a, Destinations d WHERE d.id = a.destination_id and  LOWER(a.destination_id) = LOWER(:destinationId)", nativeQuery = true)
    List<Attractions> findByDestination(@Param("destinationId") String destinationId);

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
