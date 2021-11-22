package com.challenge.ml.bsn;

import javax.servlet.http.HttpSession;

import com.challenge.ml.beans.WishListVO;

public interface WishLisBsn {
	void saveNewWishList(WishListVO wishListVO,HttpSession session);

}
