package com.ahmedsalako.htmlextractor.services;

import java.io.IOException;

import com.ahmedsalako.htmlextractor.interfaces.ResourceConnection;

/**
 * The connection factory represents an object which is responsible for creating connection to HTML resources
 * It is a creational pattern only responsible for object creation.
 * 
 * @author Ahmed
 *
 */
public class HTMLConnectionFactory {

	/**
	 * Creates the ResourceConnection instance needed to connect to HTML resources.
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public ResourceConnection createConnection(String url) throws IOException{
		
		return new HtmlConnection(url);
	}
}
