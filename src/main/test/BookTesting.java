import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.books.models.Book;
import com.books.models.xmlprocessing.Books;
import com.books.models.xmlprocessing.BooktoXML;
import com.books.models.xmlprocessing.service.impl.BookServiceImpl;

public class BookTesting {

	public static void main(String[] args) {
		BookServiceImpl bookS = new BookServiceImpl();
		Book b = new Book();
//		b.setAuthor("Author 1");
//		b.setTitle("Title 1");
//		b.setGenre("Genre 1");
//		b.setQuantity(11);
//		b.setPrice(11.99f);
//		b.setId(1);
//		System.out.println(bookS.addBook(b));
		for(Book i: bookS.getBooksByAny("Author 1", "T", "Genre 1") ){
			System.out.println(i.getTitle() + " " + i.getAuthor());
		}
	}

}
