package com.challenge.ml.bsn;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.ml.beans.UsersVO;
import com.challenge.ml.beans.WishListVO;
import com.challenge.ml.dao.BooksRepository;
import com.challenge.ml.dao.UsersRepository;
import com.challenge.ml.dao.WishListRepository;
import com.challenge.ml.entity.Book;
import com.challenge.ml.entity.Users;
import com.challenge.ml.entity.Wishlist;

@Service
public class WishListBsnImpl implements WishLisBsn{
	@Autowired
	BooksRepository booksRepository;
	@Autowired
	WishListRepository wishListRepository;
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	private ModelMapper mapper;

	@Override
	public void saveNewWishList(WishListVO wishListVO,HttpSession session) {
		System.out.println("recuperado: "+session.getAttribute("id"));
		Users userE=usersRepository.getById((int)session.getAttribute("id"));
		wishListVO.setIdUser(userE.getIdUsers());
		Wishlist newWishlist=mapper.map(wishListVO, Wishlist.class); 
		Book book=mapper.map(wishListVO.getListBooks(),Book.class);
		booksRepository.save(book);
		wishListRepository.saveAndFlush(newWishlist);
		 
	}

}
