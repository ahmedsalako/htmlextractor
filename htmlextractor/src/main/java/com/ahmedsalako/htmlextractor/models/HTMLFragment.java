package com.ahmedsalako.htmlextractor.models;

import java.util.HashMap;

/**
 * 
 * @author Ahmed
 * An HTMLFragment class represents an html node within the DOM structure.
 * This class abstracts the Element/Document classes/interfaces provided by the Jsoup framework.
 * 
 */
public class HTMLFragment
{
	private HashMap<String, String> attributes;
	private String elementId;
	private String element;
	private String text;
	
	public HTMLFragment(String element, String text, String elementId){
		this.element = element;
		this.elementId = elementId;
		this.text = text;
		this.attributes = new HashMap<>();
	}
	
	/**
	 * Returns the global unique id that was stamped on the element during its first access
	 * @return elementId
	 */
	public String id(){
		return elementId;
	}
	
	/**
	 * Returns elements ownsText
	 * @return text
	 */
	public String text(){
		return text;
	}
	
	/**
	 * For storing html attributes pertaining to the element which the HTMLFragment class represents
	 * @param name
	 * @param value
	 */
	public void setAttribute(String name, String value){
		attributes.put(name, value);
	}
	
	public String getAttribute(String name){
		return attributes.get(name).trim();
	}
}
