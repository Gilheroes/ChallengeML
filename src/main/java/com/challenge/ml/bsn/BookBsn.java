package com.challenge.ml.bsn;

import javax.servlet.http.HttpSession;

import com.challenge.ml.beans.BookVO;

public interface BookBsn {
	
	BookVO getBooksOfWishList(String nameOfList, HttpSession session);

}
