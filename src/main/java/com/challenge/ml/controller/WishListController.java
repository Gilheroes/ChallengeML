package com.challenge.ml.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.ml.beans.BookVO;
import com.challenge.ml.beans.GoogleBooksVO;
import com.challenge.ml.beans.WishListVO;
import com.challenge.ml.bsn.WishLisBsn;
import com.challenge.ml.dao.WishListRepository;
import com.challenge.ml.entity.Wishlist;

@RestController
public class WishListController {
	@Autowired
	WishLisBsn wishLisBsn;
	
	
	@PostMapping("new/wishlist")
	ResponseEntity<String>newWishList(@RequestBody List<BookVO> bookVO,HttpSession session){
		System.out.println(bookVO.toString());
		if(bookVO!=null) {
			wishLisBsn.saveNewWishList(bookVO, session);
			return ResponseEntity.status(HttpStatus.CREATED).body("Registro exitoso");
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bookVO.toString());
		
	}

}
