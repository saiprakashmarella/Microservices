package com.example.demo.dao;

import java.text.SimpleDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	private static final Logger logg=LogManager.getLogger(orderDao.class.getName());
	public TransactionResponse saveOrder(TransactionRequest resp) {
		try {
			logg.info("Saving the data to Book Service for Book id:"+resp.getBook().getAuthor());
			Book b=template.postForObject("http://BookService/Book/saveBook", resp.getBook(), Book.class);
			logg.info("Book Save successfull");
			logg.info("Saving the Payment details to payment service for payment id :"+resp.getPayment().getPaymentid());
			Payment p=template.postForObject("http://TransactionService/Payment/savePayment",resp.getPayment(),Payment.class);
			logg.info("Payment Save successfull");
			logg.info("Saving the order details");
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
			logg.info("Order save successfull");
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
