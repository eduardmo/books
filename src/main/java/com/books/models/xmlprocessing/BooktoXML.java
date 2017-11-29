package com.books.models.xmlprocessing;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class BooktoXML {

	public static void marshall(Books books) {
		try {  
			  
			   // create JAXB context and initializing Marshaller  
			   JAXBContext jaxbContext = JAXBContext.newInstance(Books.class);  
			   Marshaller jaxbMarshaller = jaxbContext.createMarshaller();  
			  
			   // for getting nice formatted output  
			   jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);  
			  
			   //specify the location and name of xml file to be created  
			   File XMLfile = new File("E:\\Fac\\an 3\\SD\\Books.xml");  
			     
			   // Writing to XML file  
			   jaxbMarshaller.marshal(books, XMLfile);   
			   // Writing to console  
//			   jaxbMarshaller.marshal(emp, System.out);   
			    
			  } catch (JAXBException e) {  
			   // some exception occured  
			   e.printStackTrace();  
			  } 
		
	}

}
