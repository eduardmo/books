package com.books.models.xmlprocessing;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XMLtoEmployees {
	
	public static Employees unmarshalEmployees(){
	try {  
	     
		   // create JAXB context and initializing Marshaller  
		   JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);  
		  
		   Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
		     
		   // specify the location and name of xml file to be read  
		   File XMLfile = new File("E:\\Fac\\an 3\\SD\\Employees.xml");  
		     
		   // this will create Java object - country from the XML file  
		   Employees employees = (Employees) jaxbUnmarshaller.unmarshal(XMLfile);  
	

		    return employees;
		  } catch (JAXBException e) {  
		   // some exception occured  
		   e.printStackTrace();  
		  }
	return null;  
	}
}
