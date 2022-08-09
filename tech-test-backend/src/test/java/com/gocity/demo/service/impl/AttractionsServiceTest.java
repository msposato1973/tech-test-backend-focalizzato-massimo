package com.gocity.demo.service.impl;

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
import com.gocity.demo.entity.Attractions;
import com.gocity.demo.entity.Destinations;
import com.gocity.demo.repository.AttractionRepository;


@ExtendWith(MockitoExtension.class)
class AttractionsServiceTest {
	

	
	private  final String ID  = buildIdUUI() ; 
	private  final String ID_DESTINATION = ID;
	
	@InjectMocks
	AttractionsService service;
	
	@Mock
    AttractionRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		service = new AttractionsService(repository);
	}
   
	

	@Test
	void testFindByDestinationsId() {

        try {
            Mockito.when(repository.findByDestination(ID_DESTINATION)).thenReturn(buildListAttractions());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
     // Execute the service that uses the mocked repository
        List<Attractions> listAttractions = service.findByDestinationsId(ID_DESTINATION);
        
     // Validate the response
        checkAssertions(listAttractions);
	}
	
	
	@Test
	void testFindAll() {

        try {
            Mockito.when(repository.findAll()).thenReturn((Iterable<Attractions>) buildIteratorAttractions());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
     // Execute the service that uses the mocked repository
        List<Attractions> listAttractions = service.findAll();
        
     // Validate the response
        Assertions.assertNotNull(listAttractions);
        Assertions.assertEquals(2, listAttractions.size());
	}
	
	private List<Attractions> buildListAttractions() {
		List<Attractions> listOptional = List.of(buildAttractions());
		return listOptional;
	}
	
	private Iterable<Attractions> buildIteratorAttractions() {
		Iterable<Attractions> iterator = List.of(buildAttractions(), buildAttractions());
	    return iterator;
	}
	
	private Attractions buildAttractions() {

	   //String name, String contactEmail, String contactPhone, Integer visitCount, Integer rating, String type, Destinations destinations
		Attractions attractions= new Attractions("TestSpring", "gmail.com", "0745678923", 0 , 0, "sring", buildDestinations());
		attractions.setId(ID);
		
		return attractions;
	
	}
	
	
	private Destinations buildDestinations() {
		Destinations destination = new Destinations("Napoli", "Napoli.google.map.gmail.com");
		destination.setId(ID_DESTINATION);
		return destination;
	}
	
	
	private String  buildIdUUI() {
	       UUID uuid = UUID.randomUUID();
	       return uuid.toString();
    }
	
	private void  checkAssertions(List<Attractions> listAttractions) {
		Assertions.assertNotNull(listAttractions);
		Assertions.assertFalse(listAttractions.isEmpty());
		
		Assertions.assertEquals(0, listAttractions.get(0).getRating());
		Assertions.assertEquals(0, listAttractions.get(0).getVisitCount());
		Assertions.assertEquals("gmail.com", listAttractions.get(0).getContactEmail());
		Assertions.assertEquals("TestSpring", listAttractions.get(0).getName());
		Assertions.assertEquals("0745678923", listAttractions.get(0).getContactPhone());
	 }

}
