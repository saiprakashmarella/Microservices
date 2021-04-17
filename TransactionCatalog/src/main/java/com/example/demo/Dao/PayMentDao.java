package com.example.demo.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Payment;
import com.example.demo.repository.PayMentRepository;

@Service
@Transactional
public class PayMentDao {
	
	@Autowired
	private PayMentRepository trepo;
	
	private static final Logger logg=LogManager.getLogger(PayMentDao.class.getName());
	
	public String savePayment(Payment p) {
		try {
			logg.info("saving the transaction for bookId:"+ p.getTransactionbookid()+" with Transaction id: "+p.getPaymentid());
			Payment p1=trepo.save(p);
			logg.info("Payment Succesfull for book Id:"+p1.getTransactionbookid()+" for Transaction Id: "+p1.getPaymentid());
			return "Payment Successfull for book Id:"+p1.getTransactionbookid();
		}catch(Exception e) {
			logg.error("Error occured while paying. Error is:"+e.toString());
			return e.toString();
		}
	}
	
	public List<Payment> getAllPaymentByUser(String name){
		try {
			logg.info("fetching all the transactions made by the user:"+name);
			return trepo.getAllPaymentsByUser(name);
		}catch(Exception e){
			logg.error("Error occured while fetching the transaction of user:"+name+". Error is "+e.toString());
			return null;
		}
	}

	public List<Payment> getAllPaymentsbyBook(Long id){
		try {
			logg.info("Fetching the details of the payments for the bookId"+id);
			return trepo.getAllPaymentsbyBook(id);
		}catch(Exception e){
			logg.error("Error occured while fetching the payments for the bookid:"+id+". Error is "+e.toString());
			return null;
		}
		
	}
}
