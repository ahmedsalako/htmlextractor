package com.ahmedsalako.htmlextractor.services;

import com.ahmedsalako.htmlextractor.interfaces.JsonWriter;
import com.ahmedsalako.htmlextractor.models.ProductResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;

/**
 * Converts ProductResult object into JSON objectr
 * @author Ahmed
 *
 */
public class JsonWriterImpl implements JsonWriter{

	private GsonBuilder gsonBuilder;
	
	@Inject
	JsonWriterImpl(GsonBuilder gsonBuilder){
		this.gsonBuilder = gsonBuilder;
	}
	
	/**
	 *  Does the serialisation of the ProductResult into JSON string
	 */
	public String toJson(ProductResult result){
		Gson gson = gsonBuilder.setPrettyPrinting()
							.disableHtmlEscaping().create();
		
		return gson.toJson(result);
	}
}
