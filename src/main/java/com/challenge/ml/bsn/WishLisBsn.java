package com.challenge.ml.bsn;

import javax.servlet.http.HttpSession;

import com.challenge.ml.beans.BookVO;

public interface WishLisBsn {
	void saveNewWishList(BookVO bookVO,HttpSession session);
}
