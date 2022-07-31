package com.gocity.demo.controller;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.gocity.demo.WebParams;
import com.gocity.demo.entity.Attractions;
import com.gocity.demo.repository.AttractionsRepository;
import com.gocity.demo.service.AttractionsService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = AttractionController.class)
@WithMockUser
public class AttractionControllerTest {
	 
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
    private AttractionsService attractionsService;
	
	@MockBean
    private  AttractionsRepository mockRepository;
	
	private List<Attractions> list;
	private static final String ID  = "3fa85f64-5717-4562-b3fc-2c963f66afa6" ; 
	private static final String ID_DESTINATION = "3fa85f64-5717-4562-b3fc-2c963f66afa6";
	
	
	private static final String URLATTRACTION_FULL = WebParams.API + WebParams.ATTRACTION_API ;
	
	@BeforeEach
	void setUp() throws Exception {
		list = buildListAttractions();
		
	}
 
	@Test
	void testFindId() throws Exception {
		  
		 when(mockRepository.findByDestination(ID_DESTINATION)).thenReturn(list);
		 String urlApi = URLATTRACTION_FULL + "?" + ID_DESTINATION;
		 Mockito.when(attractionsService.findByDestinationsId(ID_DESTINATION)).thenReturn(buildOptionalAttractions());
		 RequestBuilder requestBuilder = MockMvcRequestBuilders.get(urlApi)
				 						.param("destinationId", ID_DESTINATION)
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
	   
		return new Attractions(ID, ID_DESTINATION,  "TestSpring", "gmail.com", "0745678923", 0 , 0, "sring");
	}
	
	private Optional<Attractions> buildOptionalAttractions() {
		Optional<Attractions> listOptional = Optional.of(buildAttractions());
		return listOptional;
	}
	
	private  String buildExpectedPagination() {
		String expectedBody ="{\"pageSize\":10,\"pageNumber\":0,\"total\":0,\"results\":[{\"id\":\"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\"destination\":\"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\"name\":\"TestSpring\",\"contactEmail\":\"gmail.com\",\"contactPhone\":\"0745678923\",\"visitCount\":0,\"rating\":0,\"type\":\"sring\"}]}";
		return expectedBody;
	}
	
}
