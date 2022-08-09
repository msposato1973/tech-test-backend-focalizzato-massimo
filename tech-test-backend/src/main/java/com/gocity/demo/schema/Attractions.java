package com.gocity.demo.schema;

import java.io.Serializable;

public class Attractions implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String destination;
	private String name	;
	private String contactEmail	;
	private String contactPhone	;
	private Integer visitCount	;
	private Integer rating	;
	private String type	;
	
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public Integer getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}
	public Number getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public String getType() {
		return type;
	}
	public Attractions(String id, String destination, String name, String contactEmail, String contactPhone,
			Integer visitCount, Integer rating, String type) {
		super();
		this.id = id;
		this.destination = destination;
		this.name = name;
		this.contactEmail = contactEmail;
		this.contactPhone = contactPhone;
		this.visitCount = visitCount;
		this.rating = rating;
		this.type = type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
