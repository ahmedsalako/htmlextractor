package com.ahmedsalako.htmlextractor.crosscutting;

import com.ahmedsalako.htmlextractor.interfaces.JsonWriter;
import com.ahmedsalako.htmlextractor.interfaces.ProductRepository;
import com.ahmedsalako.htmlextractor.interfaces.ProductService;
import com.ahmedsalako.htmlextractor.interfaces.ResourceConnection;
import com.ahmedsalako.htmlextractor.services.HTMLConnectionFactory;
import com.ahmedsalako.htmlextractor.services.HtmlConnection;
import com.ahmedsalako.htmlextractor.services.JsonWriterImpl;
import com.ahmedsalako.htmlextractor.services.ProductRepositoryImpl;
import com.ahmedsalako.htmlextractor.services.ProductServiceImpl;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/***
 * 
 * @author Ahmed
 * This class represents the dependency container. 
 * It leverages google Guice as the dependency injection framework
 * All dependencies are registered and binded in this class.
 */
public class DependencyContainer extends AbstractModule {
	
	static final String url = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";
	
	@Override
	protected void configure() {
				
		bindConstant()
				.annotatedWith(Names.named("products-url"))
					.to(url);
		
		bind(ResourceConnection.class)
			.to(HtmlConnection.class);
		
		bind(HTMLConnectionFactory.class);
		
		bind(ProductRepository.class)
			.to(ProductRepositoryImpl.class);
		
		bind(ProductService.class)
			.to(ProductServiceImpl.class);
		
		bind(JsonWriter.class)
			.to(JsonWriterImpl.class);
		
		bind(GsonBuilder.class);
	}
}
