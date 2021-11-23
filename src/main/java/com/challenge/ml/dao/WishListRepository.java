package com.challenge.ml.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.challenge.ml.entity.Wishlist;

@Repository
public interface WishListRepository extends JpaRepository<Wishlist,Integer> {
	
	@Query(value="SELECT * FROM wishlist w WHERE w.id_user=:idUser",nativeQuery = true)
	Wishlist findWishByIdUser(@Param("idUser") int idUser);
	
	@Query(value="SELECT * FROM wishlist w WHERE w.name_of_list=:nameOfList",nativeQuery = true)
	Wishlist findWishByNameOfWish(@Param("name") String nameOfList);

}
