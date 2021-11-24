package com.challenge.ml.bsn;

import com.challenge.ml.beans.BookVO;
import com.challenge.ml.dao.BooksRepository;
import com.challenge.ml.dao.WishListRepository;
import com.challenge.ml.entity.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookBsnImpl implements BookBsn {

    @Autowired
    private BooksRepository bookDAO;
    @Autowired
    private WishListRepository wishListRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<BookVO> getBooksOfWishListById(Integer id, HttpSession session) {
    	try {
        List<Book> books = bookDAO.findBooksByWishListId(id);
        List<BookVO> bookVOS = new ArrayList<>();
        for (Book bookIterator : books) {
            bookVOS.add(mapper.map(bookIterator, BookVO.class));
        }
        return bookVOS;
    	}catch (Exception e) {
    		return null;
    	}
    }


}
