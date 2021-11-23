package com.challenge.ml.bsn;

import javax.servlet.http.HttpSession;

import com.challenge.ml.beans.BookVO;
import com.challenge.ml.beans.WishListVO;

public interface WishLisBsn {
	void saveNewWishList(BookVO bookVO,String nameOfWishList,HttpSession session);
	WishListVO updateWishList(BookVO bookVO,HttpSession session);
	WishListVO findWishlistByIdUser(HttpSession session);
	WishListVO deleteWishList(String nameOfList ,HttpSession session);
	
}
