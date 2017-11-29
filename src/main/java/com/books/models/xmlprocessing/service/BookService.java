package com.books.models.xmlprocessing.service;

import java.util.List;

import com.books.models.Book;

public interface BookService {
	public String addBook(Book e);
	public String editBook(Book e);
	public String deleteBook(Book e);
	public List<Book> getAllBooks();
	public List<Book> getBooksByTitle(String title);
	public List<Book> getBooksByGenre(String genre);
	public List<Book> getBooksByAuthor(String author);
	public List<Book> getBooksByAny(String author, String title, String genre);
	public List<Book> getOutOfStockBooks();
	public String sellBook(Book book, int qt);
}
