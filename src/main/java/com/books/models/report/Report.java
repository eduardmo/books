package com.books.models.report;

import java.util.List;

import com.books.models.Book;

public interface Report {
	public Object create(String file, List<Book> books);
}
