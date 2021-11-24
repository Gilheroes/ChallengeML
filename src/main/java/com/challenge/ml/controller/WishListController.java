package com.challenge.ml.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.ml.beans.BookVO;
import com.challenge.ml.beans.WishListVO;
import com.challenge.ml.bsn.WishLisBsn;

@RestController
public class WishListController {
	@Autowired
	WishLisBsn wishLisBsn;
	
	
	@PostMapping("wishlist/new/list")
	ResponseEntity<String>newWishList(@RequestBody BookVO bookVO,@Param("nameOfWishList")String nameOfWishList,HttpSession session){
		System.out.println(bookVO.toString()); 
		if(bookVO!=null && session!=null) {
			wishLisBsn.saveNewWishList(bookVO,nameOfWishList, session);
			return ResponseEntity.status(HttpStatus.CREATED).body("Registro exitoso");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en la peticion");
		
	}
	
		@PutMapping("wishlist/update/list")
		ResponseEntity<String>updateWishList(@RequestBody BookVO bookVO, HttpSession session){
			if(bookVO!=null) {
				if(session!=null) {
					wishLisBsn.updateWishList(bookVO, session);
					return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookVO.toString());
				}return ResponseEntity.status(HttpStatus.FORBIDDEN).body("La session expiro");
			}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error no se guardo la lista");
	}
		
		@GetMapping("wishlist/list/all")
		ResponseEntity<String> getAllWishlist(HttpSession session){
			if(session!=null) {
				WishListVO wishListVO=wishLisBsn.findWishlistByIdUser(session);
				if(wishListVO!=null)
					return ResponseEntity.status(HttpStatus.OK).body(wishListVO.toString());
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existen listas disponibles");
			}
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("La session expiro");
		}
				

}
