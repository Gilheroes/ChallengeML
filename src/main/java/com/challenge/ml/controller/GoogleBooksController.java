package com.challenge.ml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.ml.bsn.GoogleBooksBsn;

@RestController
public class GoogleBooksController {
	
	@Autowired
	GoogleBooksBsn googleBooksBsn;
	
	
	@GetMapping("getBook")
	ResponseEntity<String>getbook(@RequestBody String query)
	{
		return ResponseEntity.status(HttpStatus.OK).body(googleBooksBsn.getBooks(query));
	}
}
