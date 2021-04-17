package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	
	@Query("Select b from Book b where lower(b.name) like %:name%")
	List<Book> getBooksByName(@Param("name") String name);
	
	
	@Query(value="select * from book",nativeQuery=true)
	List<Book> getAllBooks();
	
	@Query("Select b from Book b where b.Author like %:Aname%")
	public List<Book> getBooksByAuthor(@Param("Aname") String name);
	
	@Procedure("Get_Book_Filter_Results")
	public List<Book> getFilterBookResults(String bname,String Aname,String costRange,String RatingRange);

}
