package com.ahmedsalako.htmlextractor.models;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The ProductInfo class represents the products derived from the product catalogue
 * @author Ahmed
 *
 */
public class ProductInfo implements Serializable
{
	private String title;
	private String size;
	private BigDecimal unitPrice;
	private String description;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSize() {
		return size;
	}
	
	public void setSize(String size) {
		this.size = size;
	}
	
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public String getUnit_Price(){
		return String.format("£%s", unitPrice.toString());
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
