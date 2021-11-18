package com.challenge.ml.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Entity;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Book", unique = true, nullable = false)
    private Integer idBook;

	@Column(name = "id_Google", nullable = false)
    private String idGoogle;

	@Column(name = "author", nullable = false)
    private String author;

	@Column(name = "title", nullable = false)
    private String title;

	@Column(name = "publisher", nullable = false)
    private String publisher;
}
