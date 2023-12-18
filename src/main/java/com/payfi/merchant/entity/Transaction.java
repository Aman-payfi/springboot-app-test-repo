package com.payfi.merchant.entity;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Transaction {

    private Long tid;
	
    private Long mid;
	
    private Long aid;
	
	private double amount;
	
	private String customerName;
	
	private String customerContactNumber;
	
	private String customerEmail;
	
    private LocalDateTime transactionTime;
    
    private String location;

	public Transaction(Long mid, Long aid, double amount, String customerName, String customerContactNumber,
			String customerEmail, LocalDateTime transactionTime, String location) {
		super();
		this.mid = mid;
		this.aid = aid;
		this.amount = amount;
		this.customerName = customerName;
		this.customerContactNumber = customerContactNumber;
		this.customerEmail = customerEmail;
		this.transactionTime = transactionTime;
		this.location = location;
	}
    
}
