package com.books.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.books.models.Book;
import com.books.models.Employee;
import com.books.models.xmlprocessing.Employees;
import com.books.models.xmlprocessing.EmployeetoXML;
import com.books.models.xmlprocessing.service.BookService;

@Controller
public class UserController {
	@Autowired
	BookService bookS;

	@RequestMapping("/user")
	public String hallo(Map<String, Object> map) {
		map.put("book", new Book());
		map.put("bookList", bookS.getAllBooks());
		return "user";
	}

	@RequestMapping(value = "user.do", method = RequestMethod.POST)
	public String add(@RequestParam("qt") int qt,@RequestParam("action") String action,
			@ModelAttribute("book") Book book, BindingResult result, Map<String, Object> map) {
		if (result.hasErrors()) {
			System.out.println("ceva");
			map.put("qtError", "A quantity to be sold must be inserted");
			map.put("bookList", bookS.getAllBooks());
		} else {
			if (qt <= 0) {
				map.put("qtError", "Quantity Must Be Greater Than 0!");
			} else {
				String msg = bookS.sellBook(book, qt);
				if (msg.toLowerCase().contains("sold")) {
					map.put("qtSuccess", "Successfully Sold!");
				} else {
					map.put("qtError", msg);
				}
			}
			map.put("bookList", bookS.getAllBooks());
		}

		return "user";
	}

	@RequestMapping(value = "user.get", method = RequestMethod.GET, params = { "title", "author", "genre", "action" })
	public String search(@RequestParam("title") String title, @RequestParam("author") String author,
			@RequestParam("genre") String genre, @RequestParam("action") String action,
			@ModelAttribute("book") Book book, BindingResult result, Map<String, Object> map) {
		switch (action.toLowerCase()) {
		case "search":
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
		return "user";
	}

}
