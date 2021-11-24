package com.challenge.ml.bsn;

import javax.servlet.http.HttpSession;
import com.challenge.ml.dao.BooksRepository;
import com.challenge.ml.dao.WishListRepository;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import com.challenge.ml.beans.BookVO;
import com.challenge.ml.beans.WishListVO;

@Service
public class BookBsnImpl implements BookBsn {
	
	@Autowired
	private BooksRepository bookDAO;
	@Autowired
	private WishListRepository wishListRepository;
	@Autowired
     private ModelMapper mapper;

	@Override
	public BookVO getBooksOfWishList(String nameOfList,HttpSession session) {
		WishListVO wishListVO=mapper.map(wishListRepository.findWishByNameOfWish(nameOfList), WishListVO.class);
		int idUser=(int)session.getAttribute("id");
		if(wishListVO.getIdUser()==idUser) {
			BookVO bookVO=mapper.map(bookDAO.findBookByIdWishList(wishListVO.getIdWishList()), BookVO.class);
			return bookVO;
		}
		return null;
		
	}
	
	

}
