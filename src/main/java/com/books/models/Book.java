package com.books.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "com.books.xmlprocessing.Books")
public class Book {
	
	
	private int id;
	private String title;
	private String author;
	private String genre;
	private int quantity;
	private float price;
	
	public String getTitle() {
		return title;
	}
	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	@XmlElement
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return genre;
	}
	@XmlElement
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getQuantity() {
		return quantity;
	}
	@XmlElement
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	@XmlElement
	public void setPrice(float price) {
		this.price = price;
	}
	public int getId() {
		return id;
	}
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}
	
	
}
