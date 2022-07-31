package com.gocity.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gocity.demo.entity.Destinations;
import com.gocity.demo.repository.DestinationRepository;

@ExtendWith(MockitoExtension.class)
class DestinationServiceTest {

	private final String ID = buildIdUUI();

	@InjectMocks
	DestinationService service;

	@Mock
	DestinationRepository repository;

	@BeforeEach
	void setUp() throws Exception {
		service = new DestinationService(repository);
	}

	@Test
	void testFindAll() {
		try {
	       Mockito.when(repository.findAll()).thenReturn(buildIteratorDestinations());
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		// Execute the service that uses the mocked repository
        List<Destinations> listDestinations = service.findAll();
        
     // Validate the response
        Assertions.assertNotNull(listDestinations);
        Assertions.assertEquals(3, listDestinations.size());
	}
	
	@Test
	void testFindById() {
		try {
	       Mockito.when(repository.findById(ID)).thenReturn(buildOptionalDestinations());
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		// Execute the service that uses the mocked repository
		Optional<Destinations> listDestinations = service.getDestinationById(ID);
        
     // Validate the response
        checkAssertions(listDestinations);
         
	}
	
	private Optional<Destinations>  buildOptionalDestinations() {	
		return Optional.of(new Destinations(ID, "TestSpring", "google") );
	}
	
	private Iterable<Destinations> buildIteratorDestinations() {
		Iterable<Destinations> iterator = List.of(
											new Destinations(ID, "TestSpring", "google"), 
											buildDestinations(), 
											buildDestinations()
										 );
	    return iterator;
	}
	
	private Destinations buildDestinations() {
		return new Destinations(buildIdUUI(), "TestSpring", "google");
	}

	private String buildIdUUI() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	private void checkAssertions(Optional<Destinations> listDestinations) {
		Assertions.assertNotNull(listDestinations);
		 
		Assertions.assertTrue(listDestinations.isPresent());
		
		Assertions.assertEquals("TestSpring", listDestinations.get().getName());
		Assertions.assertEquals("google", listDestinations.get().getImageUrl());
	}

}
