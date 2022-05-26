package com.bookcatalogue.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class CreateBookResponse {
	private int responseCode;
	private String responseMessage;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Book book;
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}

}
