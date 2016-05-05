package com.ahmedsalako.htmlextractor.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The ProductResult is a message object which contains the list of ProductInfo's and the overall total of their unit price
 * @author Ahmed
 *
 */
public class ProductResult implements Serializable
{
	private List<ProductInfo> results;
	private BigDecimal total;
	
	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public ProductResult(){
		results = new ArrayList<ProductInfo>();
	}

	public List<ProductInfo> getResults() {
		return results;
	}

	public void setResults(List<ProductInfo> results) {
		this.results = results;
	}
}
