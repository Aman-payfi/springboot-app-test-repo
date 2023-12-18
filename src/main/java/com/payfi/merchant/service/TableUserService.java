package com.payfi.merchant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.payfi.merchant.entity.MerchantBasicDetails;
import com.payfi.merchant.entity.TableUser;
//import com.payfi.merchant.exception.ResourceMismatchException;
import com.payfi.merchant.exception.ResourceNotFoundException;

public interface TableUserService {
	
	//create a Merchant
	public TableUser  saveUser(TableUser tableUser);
	
	
	public Optional<TableUser> findByMerchantOktaId(String merchantOktaId);
		
	//get single Merchant with 	Mid
	public TableUser getTableUserByMID(Long userId) throws ResourceNotFoundException;
	
	//get All Merchants
	public List<TableUser> getAllUser();
	
	/*
	//merchant registration
	public MerchantBusinessDetails registerMerchantDetails(String merchantOktaId,MerchantBusinessDetails merchantDetails) throws ResourceNotFoundException,ResourceMismatchException;
	*/
	
	//update a merchant
	public TableUser updateMerchant(Long mid, TableUser updatedTableUser);
	
	//delete a Merchant
	public void deleteMerchant(TableUser tblUser);
	
	public List<TableUser> getTableUserByAID(Long aid);

}
