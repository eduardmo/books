package com.books.models.report;

public class ReportFactory {
	Report r = null;
	public Report getReport(String type){
		if(type.equals("PDF"))
			r = new CreatePDF();
		if(type.equals("CSV"))
			r = new CreateCSV();
		return r;
	}
}
