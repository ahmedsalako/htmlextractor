package com.ahmedsalako.htmlextractor.models;

/**
 * The product detail POJO class, is used to represent the additional detail fetched for each product in the catalogue
 * The description and size fields are populated based on the extra call made to product description per each product in the catalogue
 * 
 * @author Ahmed
 *
 */
public class ProductDetail 
{
	private String description;	
	private String size;
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getSize() {
		return size;
	}
	
	public void setSize(String size) {
		this.size = size;
	}	
}
