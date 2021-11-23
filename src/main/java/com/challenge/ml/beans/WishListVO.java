package com.challenge.ml.beans;

import java.util.List;

import com.challenge.ml.entity.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishListVO {

	private int idWishList;
	private BookVO book;
	private int idUser;
	@Override
	public String toString() {
		return "WishListVO [idWishList=" + idWishList + ", book=" + book +  ", idUser=" + idUser
				+ "]";
	}
	


	
	
	
	
}
