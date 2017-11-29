package com.books.models.xmlprocessing;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.books.models.Book;

public class XMLtoBook {
	public static List<Book> unmarshalBooks(){
		try {  
		     
			   // create JAXB context and initializing Marshaller  
			   JAXBContext jaxbContext = JAXBContext.newInstance(Books.class);  
			  
			   Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
			     
			   // specify the location and name of xml file to be read  
			   File XMLfile = new File("E:\\Fac\\an 3\\SD\\Books.xml");  
			     
			   // this will create Java object - country from the XML file  
			   Books employees = (Books) jaxbUnmarshaller.unmarshal(XMLfile);  
		

			    return employees.getBooklist();
			  } catch (JAXBException e) {  
			   // some exception occured  
			   e.printStackTrace();  
			  }
		return null;  
		}
}
