package com.example.demo.Dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

@Service
@Transactional
public class BookDao {
	
	@Autowired
	private BookRepository repo;
	
	private static final Logger logg=LogManager.getLogger(BookDao.class.getName());
	
	public Book saveBook(Book b) {
		try {
			logg.info("saving the details of book id:"+b.getId());
			Book b1=repo.save(b);
			if(b1!=null) {
				logg.info("save successfull");
				return b1;
			}
			else {
				logg.error("error occured while saving the data");
				return null;
			}
		}catch(Exception e) {
			logg.error("error occured while saving the data. Error is "+e.toString());
			return null;
		}
			
	}

	public List<Book> getAllBooks(){
		try {
			logg.info("Fetching the details");
			return repo.findAll();
		}catch(Exception e) {
			logg.error("error while fetching the all book details.Error is "+ e.toString());
			return null;
		}
	}
	
	public List<Book> getBooksByName(String name){
		try {
			logg.info("Fetching the details of book by name : "+name);
			return repo.getBooksByName(name);
		}catch(Exception e) {
			logg.error("error while fetching the details by name. Error is "+ e.toString());
			return null;
		}
	}
	
	public List<Book> getBooksByAuthor(String Aname){
		try {
			logg.info("Fetching the details of book by author name :"+Aname);
			return repo.getBooksByAuthor(Aname);
		}catch(Exception e) {
			logg.error("error while fetching the details by AuthorName. Error is "+ e.toString());
			return null;
		}
	}
	
	public List<Book> getBooksByFilters(String bname,String Aname,String costRange,String RatingRange){
		try {
			logg.info("Fetching the details of book by applying the filters bname: "+Aname+" Authorname: "+Aname+" CostRange: "+costRange+" RatingRange" +RatingRange);
			return repo.getFilterBookResults(bname, Aname, costRange, RatingRange);
		}catch(Exception e){
			logg.error("error while fetching the details by applying filters. Error is "+ e.toString());
			return null;
		}	
	}
	
	public Book UpdateBook(Book b) {
		
		try {
			logg.info("Finding the book with id:"+b.getId());
			Optional<Book> b1=repo.findById(b.getId());
			if(b1.isPresent()) {
				logg.info("updating the book with id:"+b1.get().getId());
				b1.get().setName(b.getName());
				b1.get().setCost(b.getCost());
				b1.get().setAuthor(b.getAuthor());
				b1.get().setRating(b.getRating());
				logg.info("update done");
			}
			return b1.get();
		}catch(Exception e) {
			logg.error("error occured while updating the book. Error is "+e.toString());
			return null;
		}
	}
	
	public String DeleteBook(Book b) {
		try {
			logg.info("Deleting the book with id:"+b.getId());
			repo.deleteById(b.getId());
			return "book with name: "+b.getName()+" was deleted successfully";
		}catch(Exception e) {
			logg.error("error occured while deleting the book. Error is "+e.toString());
			return "Something went wrong.";
		}
	}
}
