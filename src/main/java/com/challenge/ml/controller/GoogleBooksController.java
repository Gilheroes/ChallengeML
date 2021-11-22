package com.challenge.ml.controller;


import com.challenge.ml.api.books.model.Wrapper;
import com.challenge.ml.beans.GoogleBooksVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GoogleBooksController {

	@Value("${google.books.url}")
	private String url;

    @PostMapping("/findBooks")
    ResponseEntity<Wrapper> getBooks(@RequestBody GoogleBooksVO googleBooksVO) {
        System.out.println(googleBooksVO.toString());
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("Entra books");
        ResponseEntity<Wrapper> response = restTemplate.getForEntity(url + googleBooksVO.getTitle(), Wrapper.class);
        if (response.getStatusCode().value() == 200) {
            System.out.println("Consumio template");
            return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
