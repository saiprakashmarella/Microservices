package com.example.demo.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dao.PayMentDao;
import com.example.demo.Model.Payment;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Payment")
public class PayMentController {
	
	private static final Logger logg=LogManager.getLogger(PayMentController.class.getName());
	@Autowired
	private PayMentDao dao;
	
	@GetMapping("/StatusCheck")
	public String StatusCheck() {
		
			return "Payment controller is up and running";
	}
	
	@PostMapping("/savePayment")
	public String savePayment(@RequestBody Payment p){
		try {
			logg.info(p.getPaymentby());
			return dao.savePayment(p);
		}catch(Exception e){
			return e.toString();
		}
	}
	
	@GetMapping("/getAllPaymentByUser/{name}")
	public List<Payment> getAllPaymentByUser(@PathVariable(value="name") String username){
		try {
			return dao.getAllPaymentByUser(username);
		}catch(Exception e) {
			return null;
		}
	}
	
	@GetMapping("/getAllPaymentsbyBook/{id}")
	public List<Payment> getAllPaymentsbyBook(@PathVariable(value="id") Long Bookid){
		try {
			return dao.getAllPaymentsbyBook(Bookid);
		}catch(Exception e) {
			return null;
		}
	}

}
