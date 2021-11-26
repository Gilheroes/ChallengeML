package com.challenge.ml.controller;


import com.challenge.ml.beans.BookVO;
import com.challenge.ml.beans.WishListVO;
import com.challenge.ml.bsn.WishLisBsn;

import com.challenge.ml.exception.InvalidDataException;
import com.challenge.ml.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> create(final HttpSession session) {
        try {
            if (session != null) {
                WishListVO wishListVO = wishLisBsn.saveNewWishList(session);
                return ResponseEntity.status(HttpStatus.CREATED).body(wishListVO);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en la peticion");
        } catch (InvalidDataException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (NotFoundException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Failed to creaye wishlist, Error: " + e.getMessage());
            return new ResponseEntity<>("Favor de consultar a su administrador", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Method to add book to wishlist.
     *
     * @param bookVO     Book information.
     * @param wishlistId Wishlist name.
     * @param session    User session.
     * @return Wishlist information.
     */
    @PostMapping(value = "/addBook", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> addBook(final @RequestBody BookVO bookVO, final @Param("wishlistId") Integer wishlistId, final HttpSession session) {
        try {
            System.out.println(bookVO.toString());
            if (session != null) {
                WishListVO wishListVO = wishLisBsn.addBook(bookVO, wishlistId, session);
                return ResponseEntity.status(HttpStatus.CREATED).body(wishListVO);
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("La session expiro");
        } catch (InvalidDataException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (NotFoundException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Failed to add book to wishlist, Error: " + e.getMessage());
            return new ResponseEntity<>("Favor de consultar a su administrador", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Method to update wishlist information.
     *
     * @param bookVO  Book information.
     * @param session User session.
     * @return Wishlist information.
     */
    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> update(final @RequestBody BookVO bookVO, final @Param("idWishList") Integer idWishList, final HttpSession session) {
        try {
            if (bookVO != null) {
                if (session != null) {
                    WishListVO wishListVO = wishLisBsn.updateWishList(bookVO, idWishList, session);
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(wishListVO);
                }
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("La session expiro");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error no se guardo la lista");
        } catch (InvalidDataException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (NotFoundException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Failed to add book to wishlist, Error: " + e.getMessage());
            return new ResponseEntity<>("Favor de consultar a su administrador", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Get All wishlists.
     *
     * @param session User session
     * @return A list of wishlist
     */
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> getAll(final HttpSession session) {
    	try {
	        if (session != null) {
	            List<WishListVO> wishListVO = wishLisBsn.findWishlistByIdUser(session);
	            if (wishListVO.isEmpty())
	                return ResponseEntity.status(HttpStatus.OK).body(wishListVO);
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existen listas disponibles");
	        }
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("La session expiro");
    	 } catch (InvalidDataException e) {
             log.error(e.getMessage());
             return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
         } catch (NotFoundException ex) {
             log.error(ex.getMessage());
             return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
         } catch (Exception e) {
             log.error("Failed to get wishlist, Error: " + e.getMessage());
             return new ResponseEntity<>("Favor de consultar a su administrador", HttpStatus.INTERNAL_SERVER_ERROR);
         }
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

        } catch (InvalidDataException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT); 
        } catch (NotFoundException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Failed to delete book to wishlist, Error: " + e.getMessage());
            return new ResponseEntity<>("Favor de consultar a su administrador", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
