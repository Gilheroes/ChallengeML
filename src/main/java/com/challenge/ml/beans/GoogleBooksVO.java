package com.challenge.ml.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.Getter;
import lombok.Setter;
 
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class GoogleBooksVO {
	private String id;
	private String title;
	private String[] authors;
	private String publisher;
	
	@Override
	public String toString() {
		return "GoogleBooksVO [idGoogleBook=" + id + ", title=" + title + ", author=" + authors
				+ ", publisher=" + publisher + "]";
	}
	
	

}
