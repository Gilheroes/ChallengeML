package com.challenge.ml.bsn;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.challenge.ml.beans.BookVO;
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
	public void saveNewWishList(@RequestBody BookVO bookVO,HttpSession session) {
		System.out.println("recuperado: "+session.getAttribute("id"));
		System.out.println(bookVO.toString());
		WishListVO wishListVO=new WishListVO();
		wishListVO.setBook(bookVO);
		Users userE=usersRepository.getById((int)session.getAttribute("id"));
		System.out.println(userE);
		wishListVO.setIdUser(userE.getIdUsers());
		Wishlist newWishlist=mapper.map(wishListVO, Wishlist.class);
		Book newBook=mapper.map(wishListVO.getBook(),Book.class);
		System.out.println(newBook);
		newBook=booksRepository.save(newBook);
		newWishlist.setBook(newBook);
		System.out.println(newWishlist.toString());
		wishListRepository.save(newWishlist);
	}

}
