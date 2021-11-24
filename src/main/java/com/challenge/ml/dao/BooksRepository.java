package com.challenge.ml.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.challenge.ml.entity.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
	
	@Query(value="SELECT * FROM book b WHERE b.id_WishList=:idWishList",nativeQuery = true)
	Book findBookByIdWishList(@Param("idWishList") int idWishList);
}
