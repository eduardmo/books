package com.books.models.report;

import java.util.ArrayList;
import java.util.List;

import com.books.models.Book;

public class CreateCSV implements Report {

	@Override
	public ArrayList<String> create(String file, List<Book> books) {
		ArrayList<String> rows = new ArrayList<String>();
		rows.add("Title,Author,Genre");
		rows.add("\n");
 
		for (int i = 0; i < books.size(); i++) {
			rows.add(books.get(i).getTitle()+","+books.get(i).getAuthor()+","+books.get(i).getGenre());
			rows.add("\n");
		}
		
		return rows;
	}

}
