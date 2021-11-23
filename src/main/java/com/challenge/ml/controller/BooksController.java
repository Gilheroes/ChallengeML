package com.challenge.ml.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.ml.beans.BookVO;
import com.challenge.ml.bsn.BookBsn;

@RestController
public class BooksController {

	@Autowired
	BookBsn bookBsn;
	
	@GetMapping("books/get/my/books")
	ResponseEntity<String> getMyBooks(@Param("nameOfList")String nameOfList, HttpSession session){
		if(session!=null) {
			BookVO bookVO=bookBsn.getBooksOfWishList(nameOfList, session);
			if(bookVO!=null)
				return ResponseEntity.status(HttpStatus.OK).body(bookVO.toString());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existen libros disponibles");
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("La session expiro");
	}

}
