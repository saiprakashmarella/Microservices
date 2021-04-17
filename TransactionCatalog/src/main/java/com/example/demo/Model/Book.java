package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@Table(name="Book")
public class Book {
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private long Bookid;
	private String name;
	private double cost;
	private String Author;
	private int rating;
	
	public Book(String name,double cost,String Author,int rating) {
		this.name=name;
		this.cost=cost;
		this.Author=Author;
		this.rating=rating;
	}
}
