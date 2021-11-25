package com.challenge.ml.dao;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.challenge.ml.entity.Wishlist;

/**
 * Interface used for wishlists.
 *
 * @version 0.0.1
 * @since 0.0.1
 */
@Repository
public interface WishListRepository extends JpaRepository<Wishlist,Integer> {
	
	@Query(value="SELECT w FROM Wishlist w WHERE w.idUser=:idUser")
	List<Wishlist> findWishByIdUser(@Param("idUser") int idUser);
	

}
