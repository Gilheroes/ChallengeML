package com.challenge.ml.entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wishlist")
public class Wishlist implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5866308708943279828L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Wish_list", unique = true, nullable = false)
	private int idWishList;
	
    @OneToMany(targetEntity=Book.class,mappedBy="wishlist",orphanRemoval=true)
	private List<Book> book;
	
	@Column(name="id_user",nullable = false)
	private int idUser;
	

	@Override
	public String toString() {
		return "Wishlist [idWishList=" + idWishList + ", book=" + book  + ", idUser=" + idUser
				+ "]";
	}

	


	

	

}
