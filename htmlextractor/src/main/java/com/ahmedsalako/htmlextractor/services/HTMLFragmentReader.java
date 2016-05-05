package com.ahmedsalako.htmlextractor.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import com.ahmedsalako.htmlextractor.models.HTMLFragment;

/**
 * The HTMLFragmentReader is responsible for reading the DOM elements fetched by the ResourceConnection object.
 * This class provides API methods like single, many for query ability of the DOM object in a friendlier way, and it also abstracts the Jsoup framework.
 * 
 * @author Ahmed
 *
 */
public class HTMLFragmentReader
{
	private final String elementUniqueId = "UUID";
	private Document document;
		
	HTMLFragmentReader(Document document){
		this.document = document;
	}
	
	public HTMLFragment single(String term){
		return querySingle(document, term);
	}
	
	public HTMLFragment single(String elementId, String search){
		Element element = document.getElementsByAttributeValue(elementUniqueId, elementId).first();
		
		return querySingle(element, search);
	}
	
	public List<HTMLFragment> many(String term){
		return queryMany(document, term);
	}
	
	public List<HTMLFragment> many(String elementId, String search){
		
		Element element = document.getElementsByAttributeValue(elementUniqueId, elementId).first();
		
		return queryMany(element, search);
	}
	
	private HTMLFragment querySingle(Element element, String term){
		Elements elements = element.select(term);
		
		return toFragment(elements.first(), UUID.randomUUID());
	}
	
	private List<HTMLFragment> queryMany(Element element, String term){
		Elements elements = element.select(term);
		
		return toFragments(elements);
	}	
	
	private HTMLFragment toFragment(Element element, UUID id){
			
		HTMLFragment fragment = new HTMLFragment(element.nodeName(), formatText(element.ownText()), id.toString());			
		element.attr(elementUniqueId, id.toString());
			
		for(Attribute attribute : element.attributes()){
			
			fragment.setAttribute(attribute.getKey(), attribute.getValue());
		}
		
		return fragment;
	}
	
	private List<HTMLFragment> toFragments(Elements elements){
		List<HTMLFragment> fragments = new ArrayList<HTMLFragment>();
		
		for(Element element : elements){
			fragments.add(toFragment(element, UUID.randomUUID()));
		}
		return fragments;
	}
	
	/**
	 * A workaround to replace the pound sign in html text node
	 * @param text
	 * @return formatted text
	 */
	private String formatText(String text){
		return text.replaceAll("&pound", "");
	}
	
	/**
	 * Returns the size of the entire document structure, stripping out images in kilobytes
	 * @return size
	 */
	public String getSize(){
		document.select("img").remove();
		
		String html = document.html();
		
		return String.format("%skb", html.length() / 1024);
	}
}
