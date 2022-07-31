package com.gocity.demo.entity;

import java.util.Objects;

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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
	
	@Column
	private String name;
	
	@Column
	private String imageUrl;
	
	/***
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}
	/***
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/***
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	/***
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/***
	 * 
	 * @return
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	/***
	 * 
	 * @param imageUrl
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	/***
	 * Constructor
	 * @param id
	 * @param name
	 * @param imageUrl
	 */
	public Destinations(String id, String name, String imageUrl) {
		super();
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
	}
	
	/***
	 * Def. Constructor
	 */
	public Destinations() {
		super();
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(id, name, imageUrl);
    }
	
}
