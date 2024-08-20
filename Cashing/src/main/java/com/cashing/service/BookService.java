package com.cashing.service;

import java.util.List;

import com.cashing.dto.Book;

public interface BookService {
	public Book getBookbyId(int id);
	public List<Book> getBooks();
	public Book updateBook(Book book);
	public Book updateAddress(int id,String address);
	public String deleteBook(int id);
	public String checkCacheData(String key);
	public Book addBook(Book book);
}
