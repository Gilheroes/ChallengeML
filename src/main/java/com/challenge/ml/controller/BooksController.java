package com.challenge.ml.controller;

import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.ml.beans.BookVO;
import com.challenge.ml.bsn.BookBsn;

import java.util.List;

/**
 * Rest controller for books.
 *
 * @version 0.0.1
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequestMapping(value = "/books")
public class BooksController {

    @Autowired
    private BookBsn bookBsn;

    @GetMapping(value = "/getByWishlistId", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getByWishlistId(final @RequestParam Integer wishlistId, final HttpSession session) {
        if (session != null) {
            List<BookVO> bookVO = bookBsn.getBooksOfWishListById(wishlistId, session);
            if (bookVO.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(bookVO, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("La session expiro");
    }

}
