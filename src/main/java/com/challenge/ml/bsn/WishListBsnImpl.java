package com.challenge.ml.bsn;

import java.util.*;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import com.challenge.ml.exception.InvalidDataException;
import com.challenge.ml.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;

import com.challenge.ml.beans.BookVO;
import com.challenge.ml.beans.WishListVO;
import com.challenge.ml.dao.BooksRepository;
import com.challenge.ml.dao.UsersRepository;
import com.challenge.ml.dao.WishListRepository;
import com.challenge.ml.entity.Book;
import com.challenge.ml.entity.Users;
import com.challenge.ml.entity.Wishlist;

import net.bytebuddy.implementation.bytecode.Throw;

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
    public WishListVO saveNewWishList(HttpSession session) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        try {
            Users users = usersRepository.getById((int) session.getAttribute("id"));
            Wishlist newWishlist = new Wishlist();
            newWishlist.setIdUser(users.getIdUsers());
            Wishlist savedWishlist = wishListRepository.save(newWishlist);
            return mapper.map(savedWishlist, WishListVO.class);
        } catch (Exception e) {
            System.out.println("Error " + e);
            return null;
        }

    }

    @Override
    public WishListVO updateWishList(BookVO bookVO, Integer idWishList, HttpSession session) {
        try {
            Optional<Wishlist> wishListOptional =wishListRepository.findById(idWishList);
            if (wishListOptional.isPresent()) {
	                Wishlist wishlist = wishListOptional.get();
	                WishListVO result=new WishListVO();
		            for (Book book : wishlist.getBook()) {
		                if (book.getIdBook()==bookVO.getIdBook()) {
		                	book=booksRepository.getOne(book.getIdBook());
		                	book.setAuthor(bookVO.getAuthor());
		                	book.setIdGoogle(bookVO.getIdGoogle());
		                	book.setTitle(bookVO.getTitle());
		                	book.setPublisher(bookVO.getPublisher());
		                	book=booksRepository.save(book);
		                	result.getBook().add(mapper.map(book, BookVO.class));
		                }
	            }
		            return result;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<WishListVO> findWishlistByIdUser(HttpSession session) {
        try {
        	List<Wishlist> wishlist=wishListRepository.findWishByIdUser((int) session.getAttribute("id"));
        	List<WishListVO> listVO=new ArrayList<WishListVO>();
        	if(!wishlist.isEmpty()) {
        		WishListVO wishListVO=new WishListVO();
        		for(Wishlist result:wishlist) {
        			wishListVO=mapper.map(result, WishListVO.class);
        			System.out.println(wishListVO);
        			listVO.add(wishListVO);
        		}
	        		return listVO;
        	}
            return null;
        } catch (Exception e) {
            System.out.println("error: " + e);
            return null;
        }
    }

    @Override
    public boolean deleteWishList(Integer id, HttpSession session) {
        try {
            Optional<Wishlist> wishListOptional =wishListRepository.findById(id);
            if (wishListOptional.isPresent()) {
            	Wishlist wishlist = wishListOptional.get();
                WishListVO result=new WishListVO();
                List<Book> lstBook = booksRepository.findBooksByWishListId(wishlist.getIdWishList());
                if (lstBook.size() > 0) {
                    for (Book book : lstBook) {
                        booksRepository.deleteById(book.getIdBook());
                    }
                }
                wishListRepository.delete(wishlist);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public WishListVO addBook(BookVO bookVO, int idWishList, HttpSession session) throws InvalidDataException, NotFoundException {
        // TODO Auto-generated method stub

        Optional<Wishlist> wishlistOptional = wishListRepository.findById(idWishList);
        if (wishlistOptional.isPresent()) {
            Wishlist wishlist = wishlistOptional.get();

            if (this.bookAlreadyExistInWishlist(wishlist.getIdWishList(), bookVO)) {
                throw new InvalidDataException("Ya existe ese libro en tu lista");
            }

            Book newBook = mapper.map(bookVO, Book.class);
            newBook.setWishlist(wishlist);
            Book saved = booksRepository.save(newBook);

            if (wishlist.getBook().isEmpty()) {
                List<Book> books = List.of(saved);
                wishlist.setBook(books);
            } else {
                wishlist.getBook().add(saved);
            }

            Wishlist wishListUpdated = wishListRepository.save(wishlist);

            return mapper.map(wishListUpdated, WishListVO.class);

        } else {
            throw new NotFoundException("No existe wishlist con ese identificador.");
        }
    }

    private boolean bookAlreadyExistInWishlist(final Integer idWishList, BookVO bookVO) {
        List<Book> books = booksRepository.findBooksByWishListId(idWishList);
        for (Book book : books) {
            if (book.getIdGoogle().equals(bookVO.getIdGoogle())) {
                return true;
            }
        }
        return false;
    }

}
