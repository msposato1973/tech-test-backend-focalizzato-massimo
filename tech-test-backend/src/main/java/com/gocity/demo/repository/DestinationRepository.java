package com.gocity.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gocity.demo.entity.Destinations;


/**
 * Created by STS Eclipse.
 * Project : spring-boot-one-to-one-example
 * User: msposato1973
 * Email: maxp73@gmail.com
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface DestinationRepository extends PagingAndSortingRepository<Destinations, String> {


}
