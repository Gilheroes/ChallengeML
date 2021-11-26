package com.challenge.ml.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.ml.beans.UsersVO;
import com.challenge.ml.bsn.EnrolamientoBsn;
import com.challenge.ml.exception.InvalidDataException;
import com.challenge.ml.exception.NotFoundException;


/**
 * Rest controller for users.
 *
 * @version 0.0.1
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequestMapping(value = "/users")
public class UsersController {

    @Autowired
    EnrolamientoBsn enrolamientoBsn;

    /**
     * Method to create a new user
     *
     * @param newUser Object with user information.
     * @return User information or error message.
     */
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> create(final @Valid @RequestBody UsersVO newUser) {
        try {
            if (enrolamientoBsn.validateUser(newUser)) {
                UsersVO user = enrolamientoBsn.saveNewUser(newUser);
                return new ResponseEntity<>(user, HttpStatus.CREATED);
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe");
        } catch (InvalidDataException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            log.error("Failed to create user, Error: " + e.getMessage());
            return new ResponseEntity<>("Favor de consultar a su administrador", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Method to login into account for get token authorization.
     *
     * @param usersVO     Object with user information
     * @param httpSession User session.
     * @return Token authorization or error message.
     */
    @PostMapping("/login/token")
    public ResponseEntity<?> loginToken(final @RequestBody UsersVO usersVO, final HttpSession httpSession) {
        try {
            if (enrolamientoBsn.findByUserAndPwd(usersVO, httpSession) != null) {
                return ResponseEntity.status(HttpStatus.OK).body(enrolamientoBsn.getJWTToken(usersVO.getUser_name()));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario o contrasenia no validos");
        } catch (InvalidDataException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (NotFoundException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Failed to login user, Error: " + e.getMessage());
            return new ResponseEntity<>("Favor de consultar a su administrador", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
