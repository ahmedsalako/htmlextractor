package com.ahmedsalako.htmlextractor.services;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;

import org.junit.Test;

import com.ahmedsalako.htmlextractor.interfaces.ProductRepository;
import com.ahmedsalako.htmlextractor.models.ProductInfo;
import com.ahmedsalako.htmlextractor.models.ProductResult;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HTMLExtractorIntegrationTest {

	static final String productsUrl = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";

	/**
	 * This test ensures that the Sainsbury's product list page is connected to, rather than mocking it.
	 * It simulates all the integrations points from fetching the htmls to extraction into custom HTMLFragments models
	 * and then into relevant ProductInfo objects.
	 */
	@Test
	public void ensureHTMLExtractionOfLiveConnection() {
		HTMLConnectionFactory connectionFactory = new HTMLConnectionFactory();
		
		ProductRepository productRepository = new ProductRepositoryImpl(connectionFactory, productsUrl);
		
		List<ProductInfo> productInfos = productRepository.getAll();
		
		//Using the first index of the returned productInfos to verify that the product web page was called
		ProductInfo productApricot = productInfos.get(0);
	
		assertTrue("There are only 7 products in the catalogue", productInfos.size() == 7) ;
		assertEquals("Apricots", productApricot.getDescription());
		assertEquals("Sainsbury's Apricot Ripe & Ready x5", productApricot.getTitle());		
		assertEquals("3.50", productApricot.getUnitPrice().toString());
	}

	/**
	 * This test is to complete the E2E implementation of the problem statements, main focus is the JSON serialisation.
	 * This ensures that the products retrieved from the web site is converted into ProductInfo objects.
	 * And then, the unit price is computed into total. 
	 */
	@Test
	public void ensureJsonSerialisationOfResultingProducts(){
		//setting up all the necessary dependency managements for the API to connect to the remote web page
		HTMLConnectionFactory connectionFactory = new HTMLConnectionFactory();
		ProductRepository productRepository = new ProductRepositoryImpl(connectionFactory, productsUrl);
		ProductServiceImpl productService = new ProductServiceImpl(productRepository);
		
		ProductResult productResult = productService.computeProducts();
		ProductInfo apricotProductInfo = productResult.getResults().get(0);

		//Initialise the JsonWriter service, responsible for the transformation from POJO into JSON
		JsonWriterImpl jsonWriter = new JsonWriterImpl(new GsonBuilder());
		String json = jsonWriter.toJson(productResult);
	
		//Translating the serialised string JSON back to JSON object model for unit testing purpose
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = parser.parse(json).getAsJsonObject();
		
		System.out.println(jsonObject.get("total"));
		System.out.println(productResult.getTotal().toString());
		
		assertEquals(productResult.getTotal().toString(), jsonObject.get("total").getAsString());
		assertEquals(productResult.getResults().size(), jsonObject.get("results").getAsJsonArray().size());
		
		JsonArray jsonArray = jsonObject.get("results").getAsJsonArray();
		
		JsonObject apricotJsonObject = jsonArray.get(0).getAsJsonObject();
		
		assertEquals(apricotProductInfo.getDescription(), apricotJsonObject.get("description").getAsString());
		assertEquals(apricotProductInfo.getUnitPrice().toString(), apricotJsonObject.get("unitPrice").getAsString());
		assertEquals(apricotProductInfo.getTitle(), apricotJsonObject.get("title").getAsString());
		assertEquals(apricotProductInfo.getSize(), apricotJsonObject.get("size").getAsString());

	}
}
