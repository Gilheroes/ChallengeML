package com.challenge.ml.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "book")
public class Book extends BaseEntity<Integer>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4699144633153857016L;
	@Id
	private int idBook;
	private String idGoogle;
	private String author;
	private String title;
	private String publisher;
	
	
	
	public String getIdGoogle() {
		return idGoogle;
	}
	public int getIdBook() {
		return idBook;
	}
	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}
	public void setIdGoogle(String idGoogle) {
		this.idGoogle = idGoogle;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	

	
	

}
