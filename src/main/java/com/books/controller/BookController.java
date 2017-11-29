package com.books.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.books.controller.PasswordEncryption;
import com.books.models.Book;
import com.books.models.xmlprocessing.Books;
import com.books.models.xmlprocessing.XMLtoBook;
import com.books.models.xmlprocessing.service.BookService;

@Controller
public class BookController {
	@Autowired
	BookService bookS;

	@RequestMapping("/adminbooks")
	public String book(Map<String, Object> map) {
		map.put("showEdit", "false");
		System.out.println(bookS.getAllBooks().isEmpty());
		map.put("bookList", bookS.getAllBooks());
		Book bookResult = new Book();
		map.put("book", bookResult);
		return "adminbooks";
	}

	@RequestMapping(value = "adminbooks.do", method = RequestMethod.POST)
	public String add(@RequestParam("id") int id,
			@RequestParam("action") String action, @ModelAttribute("book") Book book, BindingResult result,
			Map<String, Object> map) {
		if(result.hasErrors()){
			System.out.println(result.getAllErrors().get(0));
			for(int i = 0; i< result.getAllErrors().size();i++){
				if(result.getAllErrors().get(i).toString().contains("quantity")){
					map.put("quantityError", "Please Enter A Valid Quantity");
				}
				if(result.getAllErrors().get(i).toString().contains("price")){
					map.put("quantityError", "Please Enter A Valid Price");
				}
			}
		}
		else
		switch (action.toLowerCase()) {
		case "add":
			System.out.println("trace4");
			bookS.addBook(book);
			map.put("book", new Book());
			map.put("showEdit", "false");
			map.put("bookList", bookS.getAllBooks());

			break;
		case "edit":
			boolean ok = true;
			if(book.getTitle().length() < 3){
				map.put("titleError", "At least 3 characters required");
				ok = false;
			}
			if(book.getTitle().length() > 30){
				map.put("titleError", "At most 30 characters allowed");
				ok = false;
			}
			if(book.getTitle().isEmpty()){
				map.put("titleError", "Please Enter A Title");
				ok = false;
			}	
			if(book.getAuthor().length() < 3){
				map.put("authorError", "At least 3 characters required");
				ok = false;
			}
			if(book.getAuthor().length() > 30){
				map.put("authorError", "At most 30 characters allowed");
				ok = false;
			}
			if(book.getAuthor().isEmpty()){
				map.put("authorError", "Please Enter An Author");
				ok = false;
			}
			if(book.getGenre().length() < 3){
				map.put("genreError", "At least 3 characters required");
				ok = false;
			}
			if(book.getGenre().length() > 30){
				map.put("genreError", "At most 30 characters allowed");
				ok = false;
			}
			if(book.getGenre().isEmpty()){
				map.put("genreError", "Please Enter A Genre");
				ok = false;
			}
			if(book.getQuantity() < 0){
				map.put("quantityError", "Must Be a Positive Integer!");
				ok = false;
			}
			if(book.getPrice() < 0.0f){
				map.put("authorError", "Must Be a Positive Float!");
				ok = false;
			}
			
			if(ok){
			bookS.editBook(book);
			}
			map.put("book", new Book());
			map.put("showEdit", "false");
			map.put("bookList", bookS.getAllBooks());
			break;
		case "delete":
			bookS.deleteBook(book);
			
			break;
		}
		map.put("book", new Book());
		map.put("showEdit", "false");
		map.put("bookList", bookS.getAllBooks());
		return "adminbooks";

	}

	@RequestMapping(value = "adminbooks.get", method = RequestMethod.GET, params = { "title","author","genre", "action" })
	public String search(@RequestParam("title") String title,@RequestParam("author") String author,@RequestParam("genre") String genre,
			@RequestParam("action") String action, @ModelAttribute("book") Book book, BindingResult result,
			Map<String, Object> map) {
		switch (action.toLowerCase()) {
		case "search":
			System.out.println("ceva acolo fix aici1");
			System.out.println(title + " " + author + " " + genre);
			List<Book> searcherdBooks = bookS.getBooksByAny(author, title, genre);

			if (!searcherdBooks.isEmpty()) {

				map.put("bookList", searcherdBooks);
				map.put("showEdit", "false");
				break;
			} else {
				map.put("showEdit", "false");
				map.put("message", "Book not found");
				map.put("bookList", bookS.getAllBooks());
				break;
			}
		case "show all":
			map.put("showEdit", "false");
			map.put("bookList", bookS.getAllBooks());
			break;
		}
		Book bookResult = new Book();
		map.put("book", bookResult);
		return "adminbooks";
	}
}
