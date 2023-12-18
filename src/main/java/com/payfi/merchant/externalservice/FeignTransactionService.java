package com.payfi.merchant.externalservice;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.payfi.merchant.entity.Transaction;

@FeignClient(name="TRANSACTION")
public interface FeignTransactionService {
	
	@GetMapping("/transactions/mid/{mid}")
	List<Transaction> getTransactionByMID(@PathVariable("mid") Long mid);
	

}
