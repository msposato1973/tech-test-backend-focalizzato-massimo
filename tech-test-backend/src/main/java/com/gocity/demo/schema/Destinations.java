package com.gocity.demo.schema;

import java.io.Serializable;


public class Destinations implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name	;
	private String imageUrl	;
	
	public Destinations(String id, String name, String imageUrl) {
		super();
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
	}
	
	public Destinations() {
		super();
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
