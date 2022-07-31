package com.gocity.demo.controller;

import static org.junit.Assert.assertTrue;
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
import com.gocity.demo.entity.Destinations;
import com.gocity.demo.exception.CustomNotFoundException;
import com.gocity.demo.repository.DestinationRepository;
import com.gocity.demo.service.DestinationService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = DestinationController.class)
@WithMockUser
public class DestinationControllerTest {
	 
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
    private DestinationService destinationService;
	
	@MockBean
    private DestinationRepository mockRepository;
	
	private List<Destinations> list;
	
	private static final String ID  = "3fa85f64-5717-4562-b3fc-2c963f66afa6" ;
	private static final String ID_ERR  = "3fa85f64-5717-4562-b3fc-2c963f66afff" ;
	
	private static final String URLDESTINATION_ID  = WebParams.API + WebParams.ALL_API + WebParams.DESTINATION_API  + WebParams.ALL_API + ID;
	private static final String URLDESTINATION_ID_ERR  = WebParams.API + WebParams.ALL_API + WebParams.DESTINATION_API  + WebParams.ALL_API + ID_ERR;
	private static final String URLDESTINATION_ALL =  WebParams.API + WebParams.DESTINATION_API;
	
	@BeforeEach
	void setUp() throws Exception {
		list = buildListDestinations();
	}
 
	@Test
	void testFindAll() throws Exception {
		
		 when(mockRepository.findAll()).thenReturn(list);
		 
		 Mockito.when(destinationService.findAll()).thenReturn(list);
		 RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URLDESTINATION_ALL).accept(MediaType.APPLICATION_JSON);
		 
		 MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 String expected = buildExpectedPagination();
		 JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	/**
	 * Test method for {@link com.gocity.demo.controller.DestinationController#findById(java.lang.String)}.
	 */
	 
	@Test
	void testFindById() throws Exception {
		Destinations destinations = buildDestinations();
		when(mockRepository.findById(ID)).thenReturn(buildOptionalDestinations());
		
		Mockito.when(destinationService.getDestinationById(ID)).thenReturn(Optional.of(destinations));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URLDESTINATION_ID)
		 			.accept(MediaType.APPLICATION_JSON);
		 
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = buildExpected();
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	void testFindByIdForbiddenError() throws Exception {
		
		try {
			when(mockRepository.findById(ID_ERR)).thenThrow(CustomNotFoundException.class);
			
			Mockito.when(destinationService.getDestinationById(ID_ERR)).thenThrow(CustomNotFoundException.class);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URLDESTINATION_ID_ERR)
			 			.accept(MediaType.APPLICATION_JSON);
			 
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			String expected = buildExpectedForbiddenError();
			JSONAssert.assertNotEquals(expected, result.getResponse().getContentAsString(), false);
		} catch (Exception e) {
			assertTrue(e instanceof CustomNotFoundException);
		}
		
	}
	
	private List<Destinations> buildListDestinations() {
		List<Destinations> list = List.of(buildDestinations());
		return list;
	}
	
	private Destinations buildDestinations() {
		return new Destinations(ID, "TestSpring", "google");
	}
	
	private Optional<Destinations>  buildOptionalDestinations() {	
		return Optional.of(new Destinations(ID, "TestSpring", "google") );
	}
	
	private  String buildExpectedPagination() {
		String expected = "{\"pageSize\":10,\"pageNumber\":0,\"total\":0,\"results\":[{\"id\":\"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\"name\":\"TestSpring\",\"imageUrl\":\"google\"}]}";
		return expected;
	}
	
	private  String buildExpected() {
		String expected = "{\"id\":\"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\"name\":\"TestSpring\",\"imageUrl\":\"google\"}";
		return expected;
	}
	
	private  String buildExpectedForbiddenError() {
		// new ErrorResponse("403", ExceptionsTemplate.FORBIDDEN), HttpStatus.FORBIDDEN
		String expected = "{\"errorCode\":\"404\",\"errorMessage\":\"3fa85f64-5717-4562-b3fc-2c963f66afa6\"}";
		return expected;
	}
}
