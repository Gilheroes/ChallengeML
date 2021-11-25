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

import com.challenge.ml.beans.BookVO;
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
            WishListVO wishListVO = mapper.map(wishListRepository.findById(idWishList), WishListVO.class);
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
            return wishListVO = mapper.map(wishListRepository.findById(idWishList), WishListVO.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<WishListVO> findWishlistByIdUser(HttpSession session) {
        try {
            List<Wishlist> wishList = wishListRepository.findWishByIdUser((int) session.getAttribute("id"));
            List<WishListVO> wishListVO = new ArrayList<WishListVO>();
            wishListVO.add(mapper.map(wishList, WishListVO.class));
            return wishListVO;
        } catch (Exception e) {
            System.out.println("error: " + e);
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
