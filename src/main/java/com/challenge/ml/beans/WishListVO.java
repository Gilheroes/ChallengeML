package com.challenge.ml.beans;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishListVO {

	private int idWishList;
	private List<BookVO> listBooks;
	private int idUser;
	@Override
	public String toString() {
		return "WishListVO [idWishList=" + idWishList  +  ", listBooks="
				+ listBooks + "]";
	}

	
	
	
	
}
