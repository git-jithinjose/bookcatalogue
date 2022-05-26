package com.bookcatalogue.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookcatalogue.app.Constants.CustomMessages;
import com.bookcatalogue.app.dto.Book;
import com.bookcatalogue.app.dto.CreateBookResponse;
import com.bookcatalogue.app.dto.ListBookResponse;
import com.bookcatalogue.app.service.BookService;

@RestController
@RequestMapping("bookcatalogue")
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping("/books")
	public ListBookResponse getAllBooks() {

		List<Book> books = bookService.getAllBooks();
		ListBookResponse resp = new ListBookResponse();
		if (CollectionUtils.isEmpty(books)) {
			resp.setResponseCode(CustomMessages.FAILURERESPONSE_CODE);
			resp.setResponseMessage(CustomMessages.NO_DATA_FOUND);
			resp.setBooks(null);

		} else {
			resp.setResponseCode(CustomMessages.SUCCESSRESPONSE_CODE);
			resp.setResponseMessage(CustomMessages.DATA_FETCHED_SUCCESSFULLY);
			resp.setBooks(books);
		}

		return resp;
	}

	@GetMapping("/book/{id}")
	public CreateBookResponse getBookDetailsBookId(@PathVariable Integer id) {

		Book bookDetails = bookService.findBookById(id);
		CreateBookResponse resp = new CreateBookResponse();
		if (ObjectUtils.isEmpty(bookDetails)) {
			resp.setResponseCode(CustomMessages.FAILURERESPONSE_CODE);
			resp.setResponseMessage(CustomMessages.NO_DATA_FOUND);
			resp.setBook(null);

		} else {
			resp.setResponseCode(CustomMessages.SUCCESSRESPONSE_CODE);
			resp.setResponseMessage(CustomMessages.DATA_FETCHED_SUCCESSFULLY);
			resp.setBook(bookDetails);
		}

		return resp;
	}

	@DeleteMapping("/book/{id}")
	public CreateBookResponse deleteBookByBookId(@PathVariable Integer id) {
		Integer status = bookService.deleteByBookId(id);
		CreateBookResponse resp = new CreateBookResponse();
		if (status.equals(0)) {
			resp.setResponseCode(CustomMessages.FAILURERESPONSE_CODE);
			resp.setResponseMessage(CustomMessages.NO_DATA_FOUND);
			resp.setBook(null);

		} else {
			resp.setResponseCode(CustomMessages.SUCCESSRESPONSE_CODE);
			resp.setResponseMessage(CustomMessages.DATA_FETCHED_SUCCESSFULLY);
			resp.setBook(null);
		}

		return resp;
	}

	@PutMapping("/book")
	public CreateBookResponse createBook(@RequestBody Book book) {
		Book bookDetails = bookService.createBook(book);
		CreateBookResponse resp = new CreateBookResponse();
		if (ObjectUtils.isEmpty(bookDetails)) {
			resp.setResponseCode(CustomMessages.FAILURERESPONSE_CODE);
			resp.setResponseMessage(CustomMessages.SAVE_FAILED);
			resp.setBook(null);

		} else {
			resp.setResponseCode(CustomMessages.SUCCESSRESPONSE_CODE);
			resp.setResponseMessage(CustomMessages.SAVE_SUCCESS);
			resp.setBook(bookDetails);
		}

		return resp;
	}

	@PostMapping("/book")
	public CreateBookResponse updateBook(@RequestBody Book book) {
		CreateBookResponse resp = new CreateBookResponse();
		if (!ObjectUtils.isEmpty(book) && book.getBookId() != null && book.getBookId() > 0) {

			Book bookDetails = bookService.findBookById(book.getBookId());

			if (!ObjectUtils.isEmpty(bookDetails)) {

				Book bookdet = bookService.createBook(book);

				if (ObjectUtils.isEmpty(bookdet)) {
					resp.setResponseCode(CustomMessages.FAILURERESPONSE_CODE);
					resp.setResponseMessage(CustomMessages.UPDATE_FAILED);
					resp.setBook(null);

				} else {
					resp.setResponseCode(CustomMessages.SUCCESSRESPONSE_CODE);
					resp.setResponseMessage(CustomMessages.UPDATE_SUCCESS);
					resp.setBook(bookdet);
				}
			} else {
				resp.setResponseCode(CustomMessages.FAILURERESPONSE_CODE);
				resp.setResponseMessage(CustomMessages.BOOK_NOT_EXISTING);
				resp.setBook(null);
			}
		} else {
			resp.setResponseCode(CustomMessages.FAILURERESPONSE_CODE);
			resp.setResponseMessage(CustomMessages.UPDATE_FAILED);
			resp.setBook(null);
		}

		return resp;
	}

}
