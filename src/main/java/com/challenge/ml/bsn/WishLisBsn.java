package com.challenge.ml.bsn;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.challenge.ml.beans.BookVO;
import com.challenge.ml.beans.WishListVO;
import com.challenge.ml.exception.InvalidDataException;
import com.challenge.ml.exception.NotFoundException;

public interface WishLisBsn {
    /**
     * Create wishlist.
     *
     * @param bookVO         Book information.
     * @param idWishList 	 Wishlist identifier.
     * @param session        User session.
     */
	WishListVO saveNewWishList(HttpSession session)throws InvalidDataException, NotFoundException;

    /**
     * Update wishlist.
     *
     * @param bookVO  Book information.
     * @param session User session.
     * @return Wishlist information.
     */
    WishListVO updateWishList(final BookVO bookVO,final Integer idWishList, final HttpSession session)throws InvalidDataException, NotFoundException;

    /**
     * Get wishlist by user id.
     *
     * @param session User session.
     * @return Wishlist information.
     */
    List<WishListVO> findWishlistByIdUser(final HttpSession session)throws InvalidDataException, NotFoundException;

    /**
     * Delete wishlist information.
     *
     * @param id      wishlist identifier.
     * @param session User session.
     * @return True if the wishlist was deleted, otherwise false.
     */
    boolean deleteWishList(final Integer id, final HttpSession session)throws InvalidDataException, NotFoundException;
    
    /**
     * Create Book.
     *
     * @param bookVO         Book information.
     * @param idWishList     Wishlist identifier.
     * @param session        User session.
     */
    WishListVO addBook(final BookVO bookVO, final int idWishList, HttpSession session) throws InvalidDataException, NotFoundException;

}
