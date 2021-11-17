package com.challenge.ml.controller;

import com.challenge.ml.beans.BookVO;
import com.challenge.ml.dao.AddressRepository;
import com.challenge.ml.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
	@Autowired
	AddressRepository bookDAO;
	
	@PostMapping("users")
	public Book addbook() {
		System.out.println("Entra");
		Book book=new Book();
		book.setAuthor("J. K. Rowling");
		book.setTitle("Harry Potter");
		book.setIdGoogle("73427GR67");
		book.setPublisher("Trillas");
		BookVO bookVO=com.challenge.ml.mapper.Mapper.toVO(bookDAO.save(book));
		book=bookDAO.getById(bookVO.getIdBook());
		
		return book;
		
	}

}
