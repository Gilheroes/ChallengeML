package com.challenge.ml.bsn;

import org.springframework.stereotype.Service;

import com.challenge.ml.beans.GoogleBooksVO;

@Service
public class GoogleBooksBsnImpl implements GoogleBooksBsn {

	@Override
	public StringBuilder createRequest(GoogleBooksVO googleBooksVO) {
		StringBuilder request= new StringBuilder();
		request.append(googleBooksVO.getTitle());
		if(googleBooksVO.getAuthors()!=null) {
			request.append("+inauthor:").append(String.join(",", googleBooksVO.getAuthors()));
		}if(googleBooksVO.getPublisher()!=null) {
			request.append("+inpublisher:").append(googleBooksVO.getPublisher());
		}
		
		return request;
	}


}
