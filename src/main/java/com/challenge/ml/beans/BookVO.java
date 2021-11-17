package com.challenge.ml.beans;

public class BookVO {

	private int idBook;
	private String idGoogle;
	private String author;
	private String title;
	private String publisher;
	
	public int getIdBook() {
		return idBook;
	}
	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}
	public String getIdGoogle() {
		return idGoogle;
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
	@Override
	public String toString() {
		return "BookVO [idBook=" + idBook + ", idGoogle=" + idGoogle + ", author=" + author + ", title=" + title
				+ ", publisher=" + publisher + "]";
	}
	
	
}
