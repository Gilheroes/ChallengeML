package com.challenge.ml.mapper;

import com.challenge.ml.beans.BookVO;
import com.challenge.ml.beans.UsersVO;
import com.challenge.ml.entity.Book;
import com.challenge.ml.entity.Users;

public class Mapper {
	
	public static UsersVO toVO(Users e) {
		if(e==null) return null;
		
		UsersVO usersVO=new UsersVO();
		usersVO.setIdUsers(e.getIdUsers());
		usersVO.setUser_name(e.getUser_name());
		usersVO.setPassword(e.getPassword());
		return usersVO;
	}

	
	public static Users toEntity(UsersVO b) {
		if(b==null) return null;
		
		Users users=new Users();
		users.setId(b.getIdUsers());
		users.setUser_name(b.getUser_name());
		users.setPassword(b.getPassword());
		return users;
	}
	
	public static BookVO toVO(Book e) {
		if(e==null) return null;
		
		BookVO bookVO=new BookVO();
		bookVO.setIdBook(e.getIdBook());
		bookVO.setAuthor(e.getAuthor());
		bookVO.setIdGoogle(e.getIdGoogle());
		bookVO.setPublisher(e.getPublisher());
		bookVO.setTitle(e.getTitle());
		return bookVO;
	}
	
	public static Book toEntity(BookVO b) {
		if(b==null) return null;
		
		Book book=new Book();
		book.setIdBook(b.getIdBook());
		book.setAuthor(b.getAuthor());
		book.setIdGoogle(b.getIdGoogle());
		book.setPublisher(b.getPublisher());
		book.setTitle(b.getTitle());
		return book;
	}
}
