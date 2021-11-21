package com.challenge.ml.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookVO {

	private int idBook;
	private String idGoogle;
	private String author;
	private String title;
	private String publisher;
	
	
	@Override
	public String toString() {
		return "BookVO [idBook=" + idBook + ", idGoogle=" + idGoogle + ", author=" + author + ", title=" + title
				+ ", publisher=" + publisher + "]";
	}
	
	
}
