package com.ahmedsalako.htmlextractor.services;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.ahmedsalako.htmlextractor.interfaces.ResourceConnection;
import com.ahmedsalako.htmlextractor.models.ProductInfo;

public class ProductRepositoryImplTest {

	private final String productsUrl = "http://www.products.com";
	private final String detailsUrl = "http://www.productsDetails.com";
	
	private final String title = "Sainsbury's Avocado, Ripe & Ready x2";
	private final String unitPrice = "3.50";
	private final String description = "Avocados";
			
	private final String productDocument = String.format("<div>"
			+ "<div class=\"product\">"			
				+ "<div class=\"productInfo\">"
					+ "<h3>"
						+ "<a href=\"http://www.productsDetails.com\">%s</a>"
					+ "</h3>"
					+ "<div class=\"pricing\">"
						+ "<p class=\"pricePerUnit\">%s</p>"
					+ "</div>"			
				+ "</div>"
			+ "</div>"
		+ "</div>", title, unitPrice);
	
	private final String productDetailsDocument = String.format("<productcontent>"
				+ "<htmlcontent>"
					+ "<div class=\"productText\"><p>%s</p></div>"
				+ "</htmlcontent>"
			+ "</productcontent>", description);
	
	/**
	 * This test is to ensure the html parser was able to extract and translate the html into ProductInfo model
	 * The test mocks the actual connection to the Sainsbury's product catalogue by providing a constant string html fragments that mimic the real thing
	 * @throws IOException
	 */
	@Test
	public void ensureRepositoryGetAll() throws IOException {
		System.out.println(productDetailsDocument);
		HTMLConnectionFactory connectionFactory = Mockito.mock(HTMLConnectionFactory.class);
		
		ResourceConnection productsConnection = Mockito.mock(ResourceConnection.class);
		ResourceConnection productsDetailsConnection = Mockito.mock(ResourceConnection.class);
		
		HTMLFragmentReader productsReader = new HTMLFragmentReader(Jsoup.parse(productDocument));
		HTMLFragmentReader productsDetailsReader = new HTMLFragmentReader(Jsoup.parse(productDetailsDocument));

		
		when(productsConnection.openReader()).thenReturn(productsReader);
		when(productsDetailsConnection.openReader()).thenReturn(productsDetailsReader);		
		
		when(connectionFactory.createConnection(productsUrl)).thenReturn(productsConnection);		
		when(connectionFactory.createConnection(detailsUrl)).thenReturn(productsDetailsConnection);
		
		ProductRepositoryImpl productsRepository = new ProductRepositoryImpl(connectionFactory, productsUrl);
		
		List<ProductInfo> products = productsRepository.getAll();
		
		ProductInfo productInfo = products.stream().findFirst().get();
		
		assertEquals(title, productInfo.getTitle());
		assertEquals(unitPrice, productInfo.getUnitPrice().toString());
		assertEquals(description, productInfo.getDescription());
		
		verify(connectionFactory, times(1)).createConnection(productsUrl);
		verify(connectionFactory, times(1)).createConnection(detailsUrl);
		verify(productsConnection, times(1)).openReader();
		verify(productsDetailsConnection, times(1)).openReader();
	}

}
