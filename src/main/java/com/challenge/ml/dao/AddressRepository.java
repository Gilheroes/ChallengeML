
package com.challenge.ml.dao;

import com.challenge.ml.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends JpaRepository<Book, Integer> {
}