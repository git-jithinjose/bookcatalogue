package com.bookcatalogue.app.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.bookcatalogue.app.dto.Book;

@Service
public interface BookService {

	public Book  createBook(Book book);

	public List<Book> getAllBooks();

	public Book findBookById(Integer id);

	public Integer deleteByBookId(Integer id);

}
