package com.example.demo.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionRequest{
	
	private Book book;
	private Payment payment;
	

}
