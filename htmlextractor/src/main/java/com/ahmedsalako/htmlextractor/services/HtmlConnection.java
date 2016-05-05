package com.ahmedsalako.htmlextractor.services;

import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import com.ahmedsalako.htmlextractor.interfaces.ResourceConnection;
import com.google.inject.Inject;

/**
 * Allows connection to be made to external resource for html retrieval
 * The HtmlConnection class abstracts the Jsoup framework, and provides the flexibility for
 * adding another abstract object "The HTMLFragmentReader" for reading through the Document Model exposed by the Jsoup framework.
 * @author Ahmed
 *
 */
public class HtmlConnection implements ResourceConnection
{
	private Connection connection;	
	private String url;
	
	@Inject
	public HtmlConnection(String url){
		this.url = url;
	}
		
	/**
	 * Connects to the HTML resource/webpage and open a reader on it for reading DOM elements.
	 * After a connection has been established, an HTMLFragmentReader object is then opened to ensure querying the DOM
	 */
	public HTMLFragmentReader openReader() throws IOException{
		connection = Jsoup.connect(url);
		
		Document document = connection.get();
				
		return new HTMLFragmentReader(document);
	}
		
	/**
	 * Ensures the automatic closing of the Connection, when it is used within a try-with-resources
	 */
	public void close() throws Exception {
		//connection = null;
	}
}
