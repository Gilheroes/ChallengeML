package com.challenge.ml.bsn;

import javax.servlet.http.HttpSession;

import com.challenge.ml.beans.BookVO;
import com.challenge.ml.beans.WishListVO;

public interface WishLisBsn {
    /**
     * Create wishlist.
     *
     * @param bookVO         Book information.
     * @param nameOfWishList Wishlist name.
     * @param session        User session.
     */
    void saveNewWishList(final BookVO bookVO, final String nameOfWishList, HttpSession session);

    /**
     * Update wishlist.
     *
     * @param bookVO  Book information.
     * @param session User session.
     * @return Wishlist information.
     */
    WishListVO updateWishList(final BookVO bookVO, final HttpSession session);

    /**
     * Get wishlist by user id.
     *
     * @param session User session.
     * @return Wishlist information.
     */
    WishListVO findWishlistByIdUser(final HttpSession session);

    /**
     * Delete wishlist information.
     *
     * @param id      wishlist identifier.
     * @param session User session.
     * @return True if the wishlist was deleted, otherwise false.
     */
    boolean deleteWishList(final Integer id, final HttpSession session);

}
