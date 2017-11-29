package com.books.models.xmlprocessing.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.books.models.Book;
import com.books.models.xmlprocessing.Books;
import com.books.models.xmlprocessing.BooktoXML;
import com.books.models.xmlprocessing.XMLtoBook;
import com.books.models.xmlprocessing.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Override
	public String addBook(Book book) {
		int crtId = 0;
		List<Book> booklist = XMLtoBook.unmarshalBooks();
		for (int i = 0; i < booklist.size(); i++) {
			if (booklist.get(i).getId() == crtId) {
				crtId++;
			} else
				break;
		}
		book.setId(crtId);
		booklist.add(book);
		Books books = new Books();
		books.setBooklist(booklist);
		BooktoXML.marshall(books);

		return "Successfully Added";
	}

	@Override
	public String editBook(Book book) {
		boolean found = false;
		List<Book> booklist = XMLtoBook.unmarshalBooks();
		for (int i = 0; i < booklist.size(); i++) {
			if (booklist.get(i).getId() == book.getId()) {
				booklist.get(i).setAuthor(book.getAuthor());
				booklist.get(i).setGenre(book.getGenre());
				booklist.get(i).setTitle(book.getTitle());
				booklist.get(i).setPrice(book.getPrice());
				booklist.get(i).setQuantity(book.getQuantity());
				Books books = new Books();
				books.setBooklist(booklist);
				BooktoXML.marshall(books);
				found = true;
				break;
			}
		}
		if (!found) {
			return "Book Not Found";
		}
		return "Successfully Edited";
	}

	@Override
	public String deleteBook(Book book) {
		boolean found = false;
		List<Book> booklist = XMLtoBook.unmarshalBooks();
		for (int i = 0; i < booklist.size(); i++) {
			if (booklist.get(i).getId() == book.getId()) {
				booklist.remove(i);
				Books books = new Books();
				books.setBooklist(booklist);
				BooktoXML.marshall(books);
				found = true;
				break;
			}
		}
		if (!found) {
			return "Book Not Found";
		}
		return "Successfully Deleted";
	}

	@Override
	public List<Book> getAllBooks() {
		return XMLtoBook.unmarshalBooks();
	}

	@Override
	public List<Book> getBooksByTitle(String title) {
		List<Book> books = new ArrayList<Book>();
		for (Book i : XMLtoBook.unmarshalBooks()) {
			if (i.getTitle().equals(title)) {
				books.add(i);
			}
		}
		return books;
	}

	@Override
	public List<Book> getBooksByGenre(String genre) {
		List<Book> books = new ArrayList<Book>();
		for (Book i : XMLtoBook.unmarshalBooks()) {
			if (i.getGenre().equals(genre)) {
				books.add(i);
			}
		}
		return books;
	}

	@Override
	public List<Book> getBooksByAuthor(String author) {
		List<Book> books = new ArrayList<Book>();
		for (Book i : XMLtoBook.unmarshalBooks()) {
			if (i.getAuthor().equals(author)) {
				books.add(i);
			}
		}
		return books;
	}

	@Override
	public List<Book> getBooksByAny(String author, String title, String genre) {
		List<Book> books = new ArrayList<Book>();
		for (Book i : XMLtoBook.unmarshalBooks()) {
			if (!author.equals("") && author != null) {
				if (i.getAuthor().toLowerCase().contains(author.toLowerCase())) {
					books.add(i);
				}
			} else
				books.add(i);
		}
		List<Book> books2 = new ArrayList<Book>();
		for (Book i : books) {
			if (!title.equals("") && title != null) {
				if (i.getTitle().toLowerCase().contains(title.toLowerCase())) {
					books2.add(i);
				}
			} else
				books2.add(i);
		}
		List<Book> booksf = new ArrayList<Book>();
		for (Book i : books2) {
			if (!genre.equals("") && genre != null) {
				if (i.getGenre().toLowerCase().contains(genre.toLowerCase())) {
					booksf.add(i);
				}
			} else
				booksf.add(i);
		}
		return booksf;
	}

	@Override
	public List<Book> getOutOfStockBooks() {
		List<Book> books = new ArrayList<Book>();
		for (Book i : XMLtoBook.unmarshalBooks()) {
			if (i.getQuantity() <= 0) {
				books.add(i);
			}
		}
		return books;
	}

	@Override
	public String sellBook(Book book, int qt) {
		List<Book> bookL = this.getAllBooks();
		for (Book b : bookL) {
			if (b.getId() == book.getId()) {
				if (b.getQuantity() >= qt) {
					b.setQuantity(b.getQuantity() - qt);
					this.editBook(b);
					return "Sold!";
				} else {
					return "Not Enough Books In Stock";
				}
			}
		}

		return "Book Not Found!";
	}

}
