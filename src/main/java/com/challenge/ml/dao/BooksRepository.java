package com.challenge.ml.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.challenge.ml.entity.Book;

import java.util.List;

/**
 * Interface used for books.
 *
 * @version 0.0.1
 * @since 0.0.1
 */
@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

	@Query(value="SELECT b FROM Book b WHERE b.wishlist.idWishList=:idWishList")
	List<Book> findBooksByWishListId(@Param("idWishList") Integer idWishList);
}
