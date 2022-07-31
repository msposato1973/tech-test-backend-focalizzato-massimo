package com.gocity.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gocity.demo.entity.Destinations;

@Repository
public interface  DestinationRepository  extends PagingAndSortingRepository<Destinations, String> {
}
