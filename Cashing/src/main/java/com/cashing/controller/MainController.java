package com.cashing.controller;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cashing.dto.Book;
import com.cashing.dto.UpdateAddress;
import com.cashing.serviceimpl.BookServiceImpl;

@RestController
@RequestMapping("/library")
public class MainController {
	
	private final BookServiceImpl bookServiceImpl;
	
	public MainController(BookServiceImpl bookServiceImpl) {
		super();
		this.bookServiceImpl = bookServiceImpl;
	}

	@RequestMapping("/")
	public String checkService()
	{
		return "The server is up";
	}
	
	@PostMapping("/addbook")
	@Cacheable(cacheNames = "books",key = "#book.id")
	public Book addBook(@RequestBody Book book) 
	{
		return bookServiceImpl.addBook(book);
	}
	@PutMapping("/updatebook")
	@CachePut(cacheNames = "books" ,key = "#book.id")
	public Book updateBook(@RequestBody Book book) 
	{
		return bookServiceImpl.updateBook(book);
	}
	@DeleteMapping("/deletebook")
	@CacheEvict(cacheNames = "books", key = "#id")
	public String deleteBook(@RequestParam int id) 
	{
		return bookServiceImpl.deleteBook(id);
	}
	@PostMapping("/updateaddress")
	@CachePut(cacheNames = "books", key = "#address.id")
	public Book updateAddress(@RequestBody UpdateAddress address) 
	{
		return bookServiceImpl.updateAddress(address.getId(),address.getAddress());
	}
	@GetMapping("/getBookById")
	@Cacheable(cacheNames = "books",key = "#id")
	public Book getBookById(@RequestParam int id) 
	{
		return bookServiceImpl.getBookbyId(id);
	}
	@GetMapping("/getbooks")
	public List<Book> getBooks() 
	{
		return bookServiceImpl.getBooks();
	}

}
