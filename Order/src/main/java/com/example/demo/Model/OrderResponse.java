package com.example.demo.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="OrderDetails")
public class OrderResponse {

	@Id
	@GeneratedValue
	private Long orderid;
	private Long Bookid;
	private String bookname;
	private String authorname;
	private String orderby;
	private int orderamount;
	private String orderstatus;
	private Date orderdate;
	
}
