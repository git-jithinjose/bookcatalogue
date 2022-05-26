package com.bookcatalogue.app.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.bookcatalogue.app.dto.Book;
import com.bookcatalogue.app.repository.BookRepository;
import com.bookcatalogue.app.service.BookService;

@Service
public class BokkServiceImpl implements BookService{
	
	@Autowired
	BookRepository repo;

	@Override
	public Book  createBook(Book book) {
		Book  bookResp =repo.save(book);
		return bookResp;
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book>  books =repo.findAll();
		return books;
	}

	@Override
	public Book findBookById(Integer id) {
		Optional<Book>  bookResp =repo.findById(id);
		if(bookResp.isPresent()) {
		return bookResp.get();
		}else {
			return null;
		}
	}

	@Override
	public Integer deleteByBookId(Integer id) {
		try {
			repo.deleteById(id);
			return 1;
			
		}catch (Exception e) {
			return 0;
		}
		
	}

}
