package com.challenge.ml.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.ml.entity.Wishlist;

@Repository
public interface WishListRepository extends JpaRepository<Wishlist,Integer> {
	

}
