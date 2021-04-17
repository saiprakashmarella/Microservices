package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dao.BookDao;
import com.example.demo.model.Book;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Book")
public class BookController {

	@Autowired
	private BookDao dao;
	
	@GetMapping("/StatusCheck")
	public String StatusCheck() {
		return "Book Controller is up";
	}
	
	@PostMapping("/saveBook")
	public ResponseEntity<Book> saveBook(@RequestBody Book b) {
		try {
			return ResponseEntity.ok().body(dao.saveBook(b)); 
		}catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/getAllBooks")
	public ResponseEntity<List<Book>> getAllBooks(){
		try {
			return ResponseEntity.ok().body(dao.getAllBooks());
		}catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/getBooksByName/{name}")
	public ResponseEntity<List<Book>> getBooksByName(@PathVariable(value="name") String name){
		try {
			return ResponseEntity.ok().body(dao.getBooksByName(name));
		}catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/getBooksByAuthor/{Aname}")
	public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable(value="Aname") String Aname){
		try {
			return ResponseEntity.ok().body(dao.getBooksByAuthor(Aname));
		}catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/getBooksByFilters/{bname}/{Aname}/{costRange}/{RatingRange}")
	public ResponseEntity<List<Book>> getBooksByFilters(@PathVariable("bname") String bname,
			@PathVariable("Aname") String Aname,@PathVariable("costRange") String costRange,
			@PathVariable("RatingRange") String RatingRange){
		try {
			return ResponseEntity.ok().body(dao.getBooksByFilters(bname, Aname, costRange, RatingRange));
		}catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	@PutMapping("/UpdateBook")
	public ResponseEntity<Book> UpdateBook(@RequestBody Book b){
		try {
			return ResponseEntity.ok().body(dao.UpdateBook(b));
		}catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/DeleteBook")
	public String DeleteBook(Book b) {
		try {
			return dao.DeleteBook(b);
		}catch(Exception e) {
			return e.toString();
		}
	
	}
}
