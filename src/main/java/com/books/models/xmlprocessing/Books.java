package com.books.models.xmlprocessing;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.books.models.Book;

@XmlRootElement
public class Books {
	private List<Book> booklist = new ArrayList<Book>();

	public List<Book> getBooklist() {
		return booklist;
	}
	@XmlElement
	public void setBooklist(List<Book> bookl) {
		this.booklist = bookl;
	}
}
