package com.gocity.demo.utility;

import java.util.ArrayList;
import java.util.List;

public class ModelMapper {
	
	public static com.gocity.demo.schema.Destinations convertToDestinationsDto(com.gocity.demo.entity.Destinations entity) {
		return new com.gocity.demo.schema.Destinations(
				entity.getId(),
				entity.getName(), 
				entity.getImageUrl()
		);
	}
	
	
	//String id, String destination, String name, String contactEmail, String contactPhone, Integer visitCount, Number rating, String type
	public static com.gocity.demo.schema.Attractions convertToAttractionsDto(com.gocity.demo.entity.Attractions entity) {
		return new com.gocity.demo.schema.Attractions(
				entity.getId(),
				entity.getDestinations().getId(), 
				entity.getName(),
				entity.getContactEmail(),
				entity.getContactPhone(),
				entity.getVisitCount(),
				entity.getRating().intValue(),
				entity.getType()
		);
	}

	
	
	public static List<?> convertListEntityToDto(List list, String entityName) {
		
		List listDto;
		
		if(entityName.equalsIgnoreCase("destinations")) {
			listDto = new ArrayList<com.gocity.demo.schema.Destinations>();
			 
			list.forEach((element) -> {
				listDto.add(
						convertToDestinationsDto((com.gocity.demo.entity.Destinations) element)
				);
			});
			
		} else {
			listDto = new ArrayList<com.gocity.demo.schema.Attractions>();
			
			list.forEach((element) -> {
				listDto.add(
						convertToAttractionsDto((com.gocity.demo.entity.Attractions) element)
				);
			});
			
		}
		
		return listDto;
		
	}
}
