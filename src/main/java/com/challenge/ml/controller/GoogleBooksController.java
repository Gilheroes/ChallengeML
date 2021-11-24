package com.challenge.ml.controller;


import com.challenge.ml.api.books.model.Wrapper;
import com.challenge.ml.beans.GoogleBooksVO;
import lombok.extern.slf4j.Slf4j;
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
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Wrapper> getBooks(final @RequestBody GoogleBooksVO googleBooksVO) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Wrapper> response = restTemplate.getForEntity(url + googleBooksVO.getTitle(), Wrapper.class);
        if (response.getStatusCode().value() == 200) {
            return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
