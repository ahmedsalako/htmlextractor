package com.ahmedsalako.htmlextractor.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.ahmedsalako.htmlextractor.interfaces.ProductRepository;
import com.ahmedsalako.htmlextractor.models.ProductInfo;
import com.ahmedsalako.htmlextractor.models.ProductResult;

import junit.framework.TestCase;

public class ProductServiceImplTest{

	/**
	 * The goal of this test method is to test the product service compute method in isolation.
	 * The test method makes use of mockito to mock the actual calls to the product repository since the focus is on the unit price calculation
	 */
	@Test
	public void computeProductsUnitPrice(){
		List<ProductInfo> products = new ArrayList<ProductInfo>();
		
		ProductInfo productApricot = new ProductInfo();
		productApricot.setDescription("Sainsbury's Apricot Ripe & Ready x5");
		productApricot.setTitle("Sainsbury's Apricot Ripe & Ready x5");
		productApricot.setSize("34kb");
		productApricot.setUnitPrice(new BigDecimal(3.50));
		
		ProductInfo productAvocados = new ProductInfo();
		productAvocados.setDescription("Sainsbury's Avocado Ripe & Ready XL Loose 300g");
		productAvocados.setTitle("Sainsbury's Avocado Ripe & Ready XL Loose 300g");
		productAvocados.setSize("35kb");
		productAvocados.setUnitPrice(new BigDecimal(1.50));
		
		products.add(productApricot);
		products.add(productAvocados);
		
		BigDecimal totalUnitPrice = productApricot.getUnitPrice().add(productAvocados.getUnitPrice());
		
		ProductRepository repositoryMock = Mockito.mock(ProductRepository.class);
		
		when(repositoryMock.getAll()).thenReturn(products);		
		
		ProductServiceImpl productService = new ProductServiceImpl(repositoryMock);
		ProductResult productResult = productService.computeProducts();
		
		assertEquals(totalUnitPrice, productResult.getTotal());
		assertTrue(products.size() == 2);		
		
		verify(repositoryMock, times(1)).getAll();
	}
}
