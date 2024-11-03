package com.ad.mongo.controller;

import com.ad.mongo.repository.BookRepository;
import com.ad.mongo.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/books")
public class BookController {

	private final BookRepository bookRepository;

	public BookController(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@PostMapping
	public String saveBook(@RequestBody Book book){
		bookRepository.save(book);
		return "Added Successfully";
	}

	@GetMapping
	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	@DeleteMapping("/{id}")
	public String deleteBook(@PathVariable int id){
		bookRepository.deleteById(id);
		return "Deleted Successfully";
	}

}
