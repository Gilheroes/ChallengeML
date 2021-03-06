package com.challenge.ml.controller;


import com.challenge.ml.api.books.model.Wrapper;
import com.challenge.ml.beans.GoogleBooksVO;
import com.challenge.ml.bsn.GoogleBooksBsn;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Rest controller for google books client.
 *
 * @version 0.0.1
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequestMapping(value = "/google/books")
public class GoogleBooksController {
    @Autowired
    GoogleBooksBsn googleBooksBsn;

    /**
     * Google url in properties.
     */
    @Value("${google.books.url}")
    private String url;

    /**
     * Method to get books from google service.
     *
     * @param googleBooksVO Object with the parameter for the request.
     * @return A Wrapper class with the information.
     */
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Wrapper> getBooks(final @RequestBody GoogleBooksVO googleBooksVO) {
        RestTemplate restTemplate = new RestTemplate();
        if (!googleBooksVO.getTitle().trim().equals("")) {
            ResponseEntity<Wrapper> response = restTemplate.getForEntity(url + googleBooksBsn.createRequest(googleBooksVO), Wrapper.class);
            if (response.getStatusCode().value() == 200) {
                log.info("Success response from google service");
                return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
            }else{
                log.error("Error in service consult, Code response: "+ response.getStatusCode().value());
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
