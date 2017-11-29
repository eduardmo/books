package com.books.models.xmlprocessing;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class EmployeetoXML {
	
	
	public static void marshall(Employees emp){
	  try {  
		  
		   // create JAXB context and initializing Marshaller  
		   JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);  
		   Marshaller jaxbMarshaller = jaxbContext.createMarshaller();  
		  
		   // for getting nice formatted output  
		   jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);  
		  
		   //specify the location and name of xml file to be created  
		   File XMLfile = new File("E:\\Fac\\an 3\\SD\\Employees.xml");  
		     
		   // Writing to XML file  
		   jaxbMarshaller.marshal(emp, XMLfile);   
		   // Writing to console  
//		   jaxbMarshaller.marshal(emp, System.out);   
		    
		  } catch (JAXBException e) {  
		   // some exception occured  
		   e.printStackTrace();  
		  } 
	
	}
}
