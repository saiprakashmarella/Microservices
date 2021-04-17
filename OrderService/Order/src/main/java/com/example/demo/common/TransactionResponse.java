package com.example.demo.common;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionResponse {
	
	public Book b;
	public Payment p;
	public Long orderid;
	public Date orderdate;

}
