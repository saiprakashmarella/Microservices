package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="Payment")
public class Payment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Paymentid;
	private String Paymentby;
	private Long Transactionbookid;
	private String status;
	private int Transamount;
	
	public Payment(Long id,String by,Long bookid,String status,int amount) {
		this.Paymentid=id;
		this.Paymentby=by;
		this.status=status;
		this.Transamount=amount;
	}

}
