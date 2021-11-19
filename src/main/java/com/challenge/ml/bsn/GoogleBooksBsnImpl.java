package com.challenge.ml.bsn;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.challenge.ml.api.books.model.Wrapper;

public class GoogleBooksBsnImpl implements GoogleBooksBsn{

	@Override
	public String getBooks(String query) {
		String isbn = query;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Wrapper> entity = restTemplate.getForEntity("https://www.googleapis.com/books/v1/volumes?q=isbn:"+isbn, Wrapper.class);
		System.out.println(entity.getBody().getItems()[0].getVolumeInfo().getPublisher());
		return entity.getBody().getItems()[0].getVolumeInfo().getPublisher();
		
	}

	
	
}
