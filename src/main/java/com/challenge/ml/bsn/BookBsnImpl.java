package com.challenge.ml.bsn;

import javax.transaction.Transactional;

import com.challenge.ml.dao.AddressRepository;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import com.challenge.ml.beans.BookVO;
import com.challenge.ml.entity.Book;

@Service
@Transactional
public class BookBsnImpl implements BookBsn {
	
	@Autowired
	AddressRepository bookDAO;

	final private ModelMapper mapper = new ModelMapper();
	
	public BookVO addBook(BookVO newBook) {
		Book book=mapper.map(newBook, Book.class);
		book=bookDAO.save(book);
		BookVO bookVO=com.challenge.ml.mapper.Mapper.toVO(book);
		return bookVO;
	}

}
