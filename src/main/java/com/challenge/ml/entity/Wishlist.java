package com.challenge.ml.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wishlist")
public class Wishlist extends BaseEntity<Integer>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5866308708943279828L;
	@Id
	private int idWishList;
	
	public int getIdWishList() {
		return idWishList;
	}
	public void setIdWishList(int idWishList) {
		this.idWishList = idWishList;
	}

	
	
	

	
	

}
