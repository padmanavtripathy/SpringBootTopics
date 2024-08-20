package com.cashing.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cashing.dto.Book;
import com.cashing.repository.BookRepository;
import com.cashing.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepository;
	

	@Override
	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book updateBook(Book book) {
		try {
		Book bookrepo= getBookbyId(book.getId());
		bookrepo.setName(book.getName());
		bookrepo.setAuthor(book.getAuthor());
		bookrepo.setPage(book.getPage());
		bookrepo.setAuthor_address(book.getAuthor_address());
		return bookRepository.save(bookrepo);
		
		}
		catch (Exception e) {
			return new Book();
		}
	}

	@Override
	public Book updateAddress(int id, String address) {
		bookRepository.updateAddress(id, address);
		return bookRepository.getById(id);
	}

	@Override
	public String deleteBook(int id) {
		try {
			bookRepository.deleteById(id);
			return "Book on id:- "+id+" has been delete";
		}
		catch (Exception e) {
			return "Book on id:- "+id+" not found or not able to delete";
		}
	}

	@Override
	public String checkCacheData(String key) {
		return "cache data";
	}

	@Override
	public Book getBookbyId(int id) {
		try {
			return bookRepository.findById(id).get();
		}catch (Exception e) {
			return new Book();
		}
		
	}

	@Override
	public Book addBook(Book book) {
		
		return bookRepository.save(book);
	}

}
