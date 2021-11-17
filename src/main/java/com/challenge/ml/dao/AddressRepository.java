/*
 * Name: AddressRepository.java
 * Author: Erick Garcia <erick.garbed.isc@gmail.com>
 * Date: 21/08/2021
 */
package com.challenge.ml.dao;

import com.challenge.ml.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface used for address
 *
 * @author Erick Garcia <erick.garbed.isc@gmail.com>
 * @version 0.0.1
 * @since 0.0.1
 */
@Repository
public interface AddressRepository extends JpaRepository<Book, Integer> {
}