package com.ahmedsalako.htmlextractor.interfaces;

import com.ahmedsalako.htmlextractor.models.ProductResult;

/**
 * An interface which represents the writing of POJO object into JSON serialised objects.
 * The implementation of this interface leverages the Google Gson project, for POJO to JSON serialisation
 * @author Ahmed
 *
 */
public interface JsonWriter {
	String toJson(ProductResult result);
}
