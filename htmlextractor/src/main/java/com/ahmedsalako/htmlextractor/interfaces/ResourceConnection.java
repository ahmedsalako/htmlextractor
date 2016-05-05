package com.ahmedsalako.htmlextractor.interfaces;

import java.io.IOException;

import org.jsoup.nodes.Document;

import com.ahmedsalako.htmlextractor.services.HTMLFragmentReader;

/**
 * The interface which is used to connect to the external resource to pull HTML from the server
 * @author Ahmed
 *
 */
public interface ResourceConnection extends AutoCloseable
{
	HTMLFragmentReader openReader()throws IOException;
}
