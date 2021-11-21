package com.challenge.ml.controller;


import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.challenge.ml.api.books.model.Wrapper;
import com.challenge.ml.beans.GoogleBooksVO;

@RestController
public class GoogleBooksController {
	

	
	@GetMapping("/getBooks")
	ResponseEntity<ResponseEntity<Wrapper>> getBooks(@RequestBody GoogleBooksVO googleBooksVO) {
		  System.out.println(googleBooksVO.toString());
		  //GoogleBooksVO result=googleBooksBsn.getBooks(googleBooksVO);
	      HttpHeaders headers = new HttpHeaders();
	      RestTemplate restTemplate=new RestTemplate();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      System.out.println("Entra books");
	      ResponseEntity response = restTemplate.getForEntity("https://www.googleapis.com/books/v1/volumes?q="+googleBooksVO.getTitle(),Wrapper.class);	
	      System.out.println("Consumio template");
	      System.out.println(response.getBody());
		  return ResponseEntity.status(HttpStatus.OK).body(response);
	   }
}
