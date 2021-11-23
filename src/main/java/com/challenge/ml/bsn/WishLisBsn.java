package com.challenge.ml.bsn;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.challenge.ml.beans.BookVO;
import com.challenge.ml.beans.WishListVO;

public interface WishLisBsn {
	void saveNewWishList(BookVO bookVO,HttpSession session);
}
