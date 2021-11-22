package com.challenge.ml.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class Wishlist extends BaseEntity<Integer>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5866308708943279828L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Wish_list", unique = true, nullable = false)
	private int idWishList;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_Book")
	private List<Book> listOfBooks;
	
	@Column(name="idUser",nullable = false)
	private int idUser;


}
