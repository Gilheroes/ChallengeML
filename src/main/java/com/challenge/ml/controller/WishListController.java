package com.challenge.ml.controller;


import com.challenge.ml.beans.BookVO;
import com.challenge.ml.beans.WishListVO;
import com.challenge.ml.bsn.WishLisBsn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Rest controller for wishlist.
 *
 * @version 0.0.1
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequestMapping(value = "/wishlist")
public class WishListController {

    @Autowired
    WishLisBsn wishLisBsn;


    /**
     * Create new wishlist.
     *
     * @param bookVO         Book information.
     * @param nameOfWishList Wishlist name.
     * @param session        User session.
     * @return Wishlist information.
     */
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<String> create(final @RequestBody BookVO bookVO,final  @Param("nameOfWishList") String nameOfWishList,final  HttpSession session) {
        System.out.println(bookVO.toString());
        if (session != null) {
            wishLisBsn.saveNewWishList(bookVO, nameOfWishList, session);
            return ResponseEntity.status(HttpStatus.CREATED).body("Registro exitoso");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en la peticion");

    }

    //metodo para agregar libros a
    // wish list indepentiende del de la creacion
    // con id de la wishlist

    /**
     * Method to add book to wishlist.
     *
     * @param bookVO     Book information.
     * @param wishlistId Wishlist name.
     * @param session    User session.
     * @return Wishlist information.
     */
    @PostMapping(value = "/addBook", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<String> addBook(final @RequestBody BookVO bookVO,final  @Param("wishlistId") Integer wishlistId,final  HttpSession session) {
        System.out.println(bookVO.toString());
        if (session != null) {
            //wishLisBsn.saveNewWishList(bookVO, nameOfWishList, session);
            // agregar informacion de wishlist a repsuesta
            return ResponseEntity.status(HttpStatus.CREATED).body("Registro exitoso");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en la peticion");

    }

    //Requieres el id de la wishlist un libro puede
    // o noestar en varios wishlist

    /**
     * Method to update wishlist information.
     *
     * @param bookVO  Book information.
     * @param session User session.
     * @return Wishlist information.
     */
    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<String> update(final @RequestBody BookVO bookVO,final  HttpSession session) {
        if (bookVO != null) {
            if (session != null) {
                wishLisBsn.updateWishList(bookVO, session);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookVO.toString());
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("La session expiro");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error no se guardo la lista");
    }

    //Toma el ejemplo de bookscontroller

    /**
     * Get All wishlists.
     *
     * @param session User session
     * @return A list of wishlist
     */
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<String> getAll(final HttpSession session) {
        if (session != null) {
            WishListVO wishListVO = wishLisBsn.findWishlistByIdUser(session);
            if (wishListVO != null)
                return ResponseEntity.status(HttpStatus.OK).body(wishListVO.toString());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existen listas disponibles");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("La session expiro");
    }


    /**
     * Method to delete wishlist information.
     *
     * @param id      Wishlist identifier.
     * @param session User session.
     * @return True if the wishlist was deleted or error message.
     */
    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> delete(final @PathVariable("id") Integer id, final HttpSession session) {
        try {
            if (this.wishLisBsn.deleteWishList(id, session)) {
                return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
            } else {
                log.error("The wishlist with identifier: {} could not be removed.", id);
                return new ResponseEntity<>("The wishlist could not be removed.", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            log.error("Failed to get a wishlist by identifier: {}", id, e);
            return new ResponseEntity<>("Failed to get a wishlist by it's identifier", HttpStatus.NOT_FOUND);
        }
    }


}
