package com.ahmedsalako.htmlextractor;

import java.io.IOException;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.ahmedsalako.htmlextractor.crosscutting.DependencyContainer;
import com.ahmedsalako.htmlextractor.interfaces.JsonWriter;
import com.ahmedsalako.htmlextractor.interfaces.ProductRepository;
import com.ahmedsalako.htmlextractor.interfaces.ProductService;
import com.ahmedsalako.htmlextractor.interfaces.ResourceConnection;
import com.ahmedsalako.htmlextractor.models.ProductInfo;
import com.ahmedsalako.htmlextractor.models.ProductResult;
import com.google.inject.Guice;
import com.google.inject.Injector;


public class App 
{	
	/**
	 * The application starts by invoking the main the main method.
	 * The dependency injection container that was used for this project is based on the Google Guice project.
	 * By retrieving the Injector instance via the Google Guice, the application is able to access the dependency container to resolve object dependencies. 
	 * @param args
	 */
    public static void main( String[] args )
    {
    	Injector injector = Guice.createInjector(new DependencyContainer());
    	
        ProductService service = injector.getInstance(ProductService.class);
        JsonWriter writer = injector.getInstance(JsonWriter.class);
        
        ProductResult result = service.computeProducts();
        String json = writer.toJson(result);
        
        System.out.println(json);
    }
}
