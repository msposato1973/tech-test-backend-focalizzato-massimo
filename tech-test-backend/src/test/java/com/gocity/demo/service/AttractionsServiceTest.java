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


import com.gocity.demo.entity.Attractions;
import com.gocity.demo.repository.AttractionsRepository;

@ExtendWith(MockitoExtension.class)
class AttractionsServiceTest {
	
	
	private  final String ID  = buildIdUUI() ; 
	private  final String ID_DESTINATION = ID;
	
	@InjectMocks
	AttractionsService service;
   
    @Mock
    AttractionsRepository repository;

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
        Optional<Attractions> listAttractions = service.findByDestinationsId(ID_DESTINATION);
        
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
	
	private Attractions buildAttractions() {
		return new Attractions(buildIdUUI(), buildIdUUI(),  "TestSpring", "gmail.com", "0745678923", 0 , 0, "sring");
	}
	
	private Iterable<Attractions> buildIteratorAttractions() {
		Iterable<Attractions> iterator = List.of(buildAttractions(), buildAttractions());
	    return iterator;
	}
	
	private List<Attractions> buildListAttractions() {
		List<Attractions> listOptional = List.of(new Attractions(ID, ID_DESTINATION,  "TestSpring", "gmail.com", "0745678923", 0 , 0, "sring"));
		return listOptional;
	}
	
	private String  buildIdUUI() {
       UUID uuid = UUID.randomUUID();
       return uuid.toString();
    }
	
	private void  checkAssertions(Optional<Attractions> listAttractions) {
		Assertions.assertNotNull(listAttractions);
		Assertions.assertTrue(listAttractions.isPresent());
		
		Assertions.assertEquals(0, listAttractions.get().getRating());
		Assertions.assertEquals(0, listAttractions.get().getVisitCount());
		Assertions.assertEquals("gmail.com", listAttractions.get().getContactEmail());
		Assertions.assertEquals("TestSpring", listAttractions.get().getName());
		Assertions.assertEquals("0745678923", listAttractions.get().getContactPhone());
	 }

}
