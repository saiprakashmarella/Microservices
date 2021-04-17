package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.OrderResponse;
import com.example.demo.common.Book;
import com.example.demo.common.TransactionRequest;
import com.example.demo.common.TransactionResponse;
import com.example.demo.dao.orderDao;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Order")
public class OrderController {

	
	@Autowired
	private orderDao dao;
	
	@GetMapping("/StatusCheck")
	public String StatusCheck() {
		return "Order controller is up and running";
	}
	
	@PostMapping("/saveOrder")
	public ResponseEntity<TransactionResponse> saveOrder(@RequestBody TransactionRequest tr){
		try {
			return ResponseEntity.ok().body(dao.saveOrder(tr));
		}catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
		
	}
}
