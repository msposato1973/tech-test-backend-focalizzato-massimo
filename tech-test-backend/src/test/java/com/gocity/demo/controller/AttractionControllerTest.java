package com.gocity.demo.controller;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.gocity.demo.entity.Attractions;
import com.gocity.demo.entity.Destinations;
import com.gocity.demo.repository.AttractionRepository;
import com.gocity.demo.service.impl.AttractionsService;


@WebMvcTest(value = AttractionController.class)
@WithMockUser
class AttractionControllerTest {
	
	private List<Attractions> listAttractions;
	private static final String ID  = "3fa85f64-5717-4562-b3fc-2c963f66afa6" ; 
	private static final String ID_DESTINATION = "3fa85f64-5717-4562-b3fc-2c963f66afa6";
	
	
	private static final String URL_ATTRACTION_ID = "/api/attractions/?destinationId=" + ID_DESTINATION;
	private static final String URL_ATTRACTION_ALL = "/api/attractions/";
	
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
    private AttractionsService attractionsService;
	
	@MockBean
    private  AttractionRepository mockRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		 
		listAttractions = buildListAttractions();
	}
	

	@Test
	void testFindId() throws Exception {
		 List<Attractions> attractions = buildResultAttractions();
		 
		 when(mockRepository.findByDestination(ID_DESTINATION)).thenReturn(attractions);
		 Mockito.when(attractionsService.findByDestinationsId(ID_DESTINATION)).thenReturn(listAttractions);
		 RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL_ATTRACTION_ID)
				 						.accept(MediaType.APPLICATION_JSON);
		 
		 MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 String expected = buildExpectedPagination();
		 JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	
	private List<Attractions> buildListAttractions() {
		List<Attractions> listAttractions = List.of(buildAttractions());
		return listAttractions;
	}
	
	private Attractions buildAttractions() {
	   //String name, String contactEmail, String contactPhone, Integer visitCount, Integer rating, String type, Destinations destinations
		Attractions attractions= new Attractions("Napoli", "gmail.com", "0745678923", 0 , 0, "sring", buildDestinations());
		attractions.setId(ID);
		
		return attractions;
	}
	
	private List<Attractions> buildResultAttractions() {
		List<Attractions> listAttractions = List.of(buildAttractions());
		return listAttractions;
	}
	
	private  String buildExpectedPagination() {
		String expectedBody ="{\"pageSize\":10,\"pageNumber\":0,\"total\":0,\"results\":[{\"id\":\"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\"destination\":\"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\"name\":\"Napoli\",\"contactEmail\":\"gmail.com\",\"contactPhone\":\"0745678923\",\"visitCount\":0,\"rating\":0,\"type\":\"sring\"}]}";
		return expectedBody;
	}
	
	private Destinations buildDestinations() {
		Destinations destination = new Destinations("Napoli", "Napoli.google.map.gmail.com");
		destination.setId(ID_DESTINATION);
		return destination;
	}

}
