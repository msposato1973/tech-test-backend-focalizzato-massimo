package com.gocity.demo.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Attractions")
public class Attractions {

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
	
	@Column
	private String destination;
	
	@Column
	private String name;
	
	@Column
	private String contactEmail;
	
	@Column
	private String contactPhone;
	
	@Column
	private int visitCount;
	
	@Column
	private int rating;
	
	@Column
	private String type;
	
	public Attractions(String id, String destination, 
						String name,  String contactEmail, 
						String contactPhone, int visitCount, 
						int rating, String type) 
	{
		
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
	
	public Attractions() {
		super();
	}

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

	public int getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, destination, name, contactEmail, contactPhone, visitCount, rating, type);
	}
	
}
