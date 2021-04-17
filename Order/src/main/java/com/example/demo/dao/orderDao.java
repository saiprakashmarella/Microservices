package com.example.demo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Model.OrderResponse;
import com.example.demo.common.Book;
import com.example.demo.common.Payment;
import com.example.demo.common.TransactionRequest;
import com.example.demo.common.TransactionResponse;
import com.example.demo.repository.OrderRepository;

@Service
@Transactional
public class orderDao {

	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private RestTemplate template;
	
	public TransactionResponse saveOrder(TransactionRequest resp) {
		try {
			
			Book b=template.postForObject("http://localhost:8071/Book/saveBook", resp.getBook(), Book.class);
			Payment p=template.postForObject("http://localhost:8081/Payment/savePayment",resp.getPayment(),Payment.class);
			OrderResponse order=new OrderResponse();
			order.setBookid(b.getId());
			order.setBookname(b.getName());
			order.setAuthorname(b.getAuthor());
			order.setOrderamount(p.getTransamount());
			order.setOrderby(p.getPaymentby());
			order.setOrderid(p.getPaymentid());
			order.setOrderstatus(p.getStatus());
			Date d=new Date();
			order.setOrderdate(d);
			order=repo.save(order);
			TransactionResponse tr=new TransactionResponse();
			tr.setB(b);
			tr.setP(p);
			tr.setOrderid(order.getOrderid());
			
			//SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			tr.setOrderdate(d);
			return tr;
			
		}catch(Exception e) {
			return null;
		}
	}
}