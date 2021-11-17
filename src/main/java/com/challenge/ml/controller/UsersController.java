package com.challenge.ml.controller;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.ml.beans.BookVO;
import com.challenge.ml.dao.BookDAO;
import com.challenge.ml.entity.Book;

@RestController
public class UsersController {
	@Autowired
	BookDAO bookDAO;
	
	@PostMapping("users")
	public Book addbook() {
		System.out.println("Entra");
		Book book=new Book();
		book.setAuthor("J. K. Rowling");
		book.setTitle("Harry Potter");
		book.setIdGoogle("73427GR67");
		book.setPublisher("Trillas");
		BookVO bookVO=com.challenge.ml.mapper.Mapper.toVO(bookDAO.save(book));
		book=bookDAO.find(bookVO.getIdBook());
		
		return book;
		
	}

}
