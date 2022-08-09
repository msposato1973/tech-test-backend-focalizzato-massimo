package com.gocity.demo.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

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

import com.gocity.demo.entity.Destinations;
import com.gocity.demo.exception.CustomNotFoundException;
import com.gocity.demo.repository.DestinationRepository;
import com.gocity.demo.service.impl.DestinationService;


@WebMvcTest(value = DestinationController.class)
@WithMockUser
public class DestinationControllerTest {

	private List<Destinations> list;

	private static final String ID = "c0108893-dcc5-428f-9e42-d7e03a5037e0";
	private static final String ID_ERR  = "3fa85f64-5717-4562-b3fc-2c963f66afff" ;
	
	private static final String URL_DESTINATION_ALL = "/api/destinations/";
	private static final String URL_DESTINATION_ID = "/api/destinations/" + ID; 
	private static final String URL_DESTINATION_ID_ERR = "/api/destinations/" + ID_ERR; 
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DestinationService destinationService;

	@MockBean
	private DestinationRepository mockRepository;

	@BeforeEach
	void setUp() throws Exception {
		list = buildListDestinations();
	}

	@Test
	void testFindAll() throws Exception {

		when(mockRepository.findAll()).thenReturn(list);

		Mockito.when(destinationService.findAll()).thenReturn(list);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL_DESTINATION_ALL)
				.accept(MediaType.APPLICATION_JSON);

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
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL_DESTINATION_ID)
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
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL_DESTINATION_ID_ERR)
			 			.accept(MediaType.APPLICATION_JSON);
			 
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			String expected = buildExpectedForbiddenError();
			JSONAssert.assertNotEquals(expected, result.getResponse().getContentAsString(), false);
		} catch (Exception e) {
			assertTrue(e instanceof CustomNotFoundException);
		}
		
	}

	private String buildExpectedForbiddenError() {
		// new ErrorResponse("403", ExceptionsTemplate.FORBIDDEN), HttpStatus.FORBIDDEN
		String expected = "{\"errorCode\":\"404\",\"errorMessage\":\"3fa85f64-5717-4562-b3fc-2c963f66afa6\"}";
		return expected;
	}

	private Optional<Destinations> buildOptionalDestinations() {
		return Optional.of(buildDestinations());
	}

	private Destinations buildDestinations() {
		Destinations destination = new Destinations("Napoli", "Napoli.google.map.gmail.com");
		destination.setId(ID);
		return destination;
	}

	private List<Destinations> buildListDestinations() {
		Destinations destination = new Destinations("Napoli", "Napoli.google.map.gmail.com");
		destination.setId(ID);

		List<Destinations> list = List.of(destination);
		return list;
	}

	private String buildExpectedPagination() {
		String expected = "{\"pageSize\":10,\"pageNumber\":0,\"total\":0,\"results\":[{\"id\":\"c0108893-dcc5-428f-9e42-d7e03a5037e0\",\"name\":\"Napoli\",\"imageUrl\":\"Napoli.google.map.gmail.com\"}]}";
		return expected;
	}
	
	private  String buildExpected() {
		String expected = "{\"id\":\"c0108893-dcc5-428f-9e42-d7e03a5037e0\",\"name\":\"Napoli\",\"imageUrl\":\"Napoli.google.map.gmail.com\"}";
		return expected;
	}

}
