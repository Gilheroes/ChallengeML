package com.challenge.ml.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.ml.entity.Book;

public interface BooksRepository extends JpaRepository<Book, Integer> {

}
