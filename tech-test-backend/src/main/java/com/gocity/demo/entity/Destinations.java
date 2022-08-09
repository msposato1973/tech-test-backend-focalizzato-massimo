package com.gocity.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Destinations")
public class Destinations {
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(length = 36, name = "id")
	private String id;
	
	@Column(name = "name")
	private String name	;
	
	@Column(name = "imageUrl")
	private String imageUrl	;
	
	public Destinations(String name, String imageUrl) {
		super();
		this.name = name;
		this.imageUrl = imageUrl;
	}
	
	public Destinations() {
		super();
	}
	

	@Override
	public String toString() {
		return "Destinations [id=" + id + ", name=" + name + ", imageUrl=" + imageUrl + "]";
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	
}
