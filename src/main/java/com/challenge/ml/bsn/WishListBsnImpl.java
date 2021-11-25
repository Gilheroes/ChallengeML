package com.challenge.ml.bsn;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class WishListBsnImpl implements WishLisBsn {

	@Autowired
	private BooksRepository booksRepository;
	@Autowired
	private WishListRepository wishListRepository;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private ModelMapper mapper;

	@Override
	public WishListVO saveNewWishList(BookVO bookVO, HttpSession session) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		try {
			WishListVO wishListVO = new WishListVO();
			Users users = usersRepository.getById((int) session.getAttribute("id"));
			wishListVO.setIdUser(users.getIdUsers());
			wishListVO.getBook().add(bookVO);
			Wishlist newWishlist = mapper.map(wishListVO, Wishlist.class);
				newWishlist = wishListRepository.save(newWishlist);
				wishListVO = mapper.map(newWishlist, WishListVO.class);
				return wishListVO;
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public WishListVO updateWishList(BookVO bookVO,Integer idWishList, HttpSession session) {
		try {
			WishListVO wishListVO = mapper.map(wishListRepository.findById(idWishList),WishListVO.class);
			for (BookVO bookVOl : wishListVO.getBook()) {
				Book book = mapper.map(bookVOl, Book.class);
				if (bookVOl.getTitle().equals(bookVO.getTitle())
						&& bookVOl.getIdGoogle().equals(bookVO.getIdGoogle())) {
					booksRepository.delete(book);
				} else {
					bookVOl.setWishListVO(wishListVO);
					book = mapper.map(bookVOl, Book.class);
					booksRepository.save(book);
				}
			}
			return wishListVO = mapper.map(wishListRepository.findById(idWishList),WishListVO.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public WishListVO findWishlistByIdUser(HttpSession session) {
		try {
			WishListVO wishListVO = mapper.map(wishListRepository.findWishByIdUser((int) session.getAttribute("id")),
					WishListVO.class);
			return wishListVO;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean deleteWishList(Integer id, HttpSession session) {
		try {
			Wishlist wishList = mapper.map(wishListRepository.findById(id), Wishlist.class);
			if (wishList != null) {
				List<Book> lstBook = booksRepository.findBooksByWishListId(wishList.getIdWishList());
				if (lstBook.size() > 0) {
					for (Book book : lstBook) {
						booksRepository.deleteById(book.getIdBook());
					}
				}
				wishListRepository.delete(wishList);
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public BookVO addBook(BookVO bookVO, int idWishList, HttpSession session) {
		// TODO Auto-generated method stub
		try {
			WishListVO wishVo=mapper.map(wishListRepository.findById(idWishList), WishListVO.class);
			if (wishVo != null) {
				System.out.println("Entra a wishvo");
				Wishlist wishlist=mapper.map(wishVo, Wishlist.class);
				System.out.println(idWishList);
				List<Book> book =booksRepository.findBooksByWishListId(idWishList);
				Book newBook=mapper.map(bookVO, Book.class);
				newBook.setWishlist(wishlist);
				BookVO bookResult;
				if(!book.isEmpty()) {
					System.out.println("no es vacia");
					for(Book findBook:book) {
						if(findBook.getIdGoogle()==newBook.getIdGoogle()) {
							System.out.println("existe");
							return null;
						}
					}
					System.out.println("inserta");
						return bookResult=mapper.map(booksRepository.save(newBook), BookVO.class);
				}else {
					System.out.println("inserta");
					 	return bookResult=mapper.map(booksRepository.save(newBook), BookVO.class);
				}
			}
			return null;
		} catch (Exception e) {
			System.out.println("error: "+e);
			return null;
		}
	}

}
