package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Payment;


@Repository
public interface PayMentRepository extends JpaRepository<Payment, Long>{
	
	@Query("select p from Payment p where PaymentBy=?1")
	List<Payment> getAllPaymentsByUser(String name);

	@Query("select p from Payment p where TransactionBookId=?1")
	List<Payment> getAllPaymentsbyBook(Long id);

}
