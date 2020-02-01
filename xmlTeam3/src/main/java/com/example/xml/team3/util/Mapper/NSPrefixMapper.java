package com.example.xml.team3.util.Mapper;

import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.xml.bind.annotation.XmlTransient;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

@XmlTransient
public class NSPrefixMapper extends NamespacePrefixMapper {

	private HashMap<String, String> mappings;

	public NSPrefixMapper() { 
		
		// Inicijalizacija mape prefiksa
		mappings = new LinkedHashMap<String, String>(); 
		setDefaultMappings(); 
	}

	protected void setDefaultMappings() { 
		
		// PoniÅ¡tava prethodna mapiranja
		clear();

		// Za default namespace prefiks postaviti na "" 
		addMapping("http://www.ftn.uns.ac.rs/team3", ""); 
		addMapping("http://www.w3.org/2001/XMLSchema-instance", "xsi"); 
		addMapping("http://java.sun.com/xml/ns/jaxb", "model"); 
	}

	public void addMapping(String uri, String prefix){
		mappings.put(uri, prefix);
	} 
	
	public String getMapping(String uri){
		return (String)mappings.get(uri);
	} 
	public HashMap<String, String> getMappings(){
		return mappings;
	} 
	public void clear(){
		mappings.clear();
	}

	/**
	 * Metoda vraÄ‡a preferirani prefiks za zadati namespace. 
	 */
	public String getPreferredPrefix(String namespaceURI, String suggestion, boolean requirePrefix) { 
		String preferredPrefix = getMapping(namespaceURI); 
		if(preferredPrefix != null)
			return preferredPrefix;
		return suggestion; 
	} 

}

