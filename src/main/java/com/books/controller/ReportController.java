package com.books.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.books.models.report.CreateCSV;
import com.books.models.report.CreatePDF;
import com.books.models.report.Report;
import com.books.models.report.ReportFactory;
import com.books.models.xmlprocessing.service.BookService;
@Controller
public class ReportController {
	@Autowired
	BookService bookS;
	
	Report rep;
	
	@RequestMapping("/reports")
	public String reports(){
		return "reports";
	}
	
	@RequestMapping(value = "/download")
	public void downloadPDF(@RequestParam("action") String action, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		ReportFactory factory = new ReportFactory();
		
		final ServletContext servletContext = request.getSession().getServletContext();
	    final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
	    final String temperotyFilePath = tempDirectory.getAbsolutePath();

	    switch(action.toLowerCase()){
	    case "download pdf":
	    	String fileName = "Books.pdf";
		    response.setContentType("application/pdf");
		    response.setHeader("Content-disposition", "attachment; filename="+ fileName);
	 
		    try {
		    	rep = factory.getReport("PDF");
		        rep.create(temperotyFilePath+"\\"+fileName,bookS.getOutOfStockBooks());
		        ByteArrayOutputStream baos = new ByteArrayOutputStream();
		        baos = convertPDFToByteArrayOutputStream(temperotyFilePath+"\\"+fileName);
		        OutputStream os = response.getOutputStream();
		        baos.writeTo(os);
		        os.flush();
		    } catch (Exception e1) {
		        e1.printStackTrace();
		    }
	    	break;
	    case "download csv":
	    	response.setContentType("text/csv");
			String reportName = "Books.csv";
			response.setHeader("Content-disposition", "attachment;filename="+reportName);
			fileName = "Books.csv";
			
			rep = factory.getReport("CSV");
			
			ArrayList<String> rows = (ArrayList<String>) rep.create(fileName, bookS.getOutOfStockBooks());
			
			Iterator<String> iter = rows.iterator();
			while (iter.hasNext()) {
				String outputString = (String) iter.next();
				response.getOutputStream().print(outputString);
			}
	 
			response.getOutputStream().flush();
	    }
	    
 
	}
	
	private ByteArrayOutputStream convertPDFToByteArrayOutputStream(String fileName) {
 
		InputStream inputStream = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
 
			inputStream = new FileInputStream(fileName);
			byte[] buffer = new byte[1024];
			baos = new ByteArrayOutputStream();
 
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesRead);
			}
 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return baos;
	}
}
