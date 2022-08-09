package com.gocity.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Attractions")
public class Attractions {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(length = 36)
	private String id;

	@OneToOne
	@JoinColumn(name = "destination_id", referencedColumnName = "id")
	private Destinations destinations;

	public Attractions(String name, String contactEmail, String contactPhone, Integer visitCount, Integer rating,
			String type, Destinations destinations) {
		super();
		this.name = name;
		this.contactEmail = contactEmail;
		this.contactPhone = contactPhone;
		this.visitCount = visitCount;
		this.rating = rating;
		this.type = type;
		this.destinations = destinations;
	}
	
	public Attractions() {
		super();
	}

	private String name;
	private String contactEmail;
	private String contactPhone;
	private Integer visitCount;
	private Integer rating;
	private String type;

	public Destinations getDestinations() {
		return destinations;
	}

	public void setDestinations(Destinations destinations) {
		this.destinations = destinations;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public void setType(String type) {
		this.type = type;
	}

}
