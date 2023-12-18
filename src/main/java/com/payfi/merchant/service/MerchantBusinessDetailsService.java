package com.payfi.merchant.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.payfi.merchant.entity.MerchantBasicDetails;
import com.payfi.merchant.entity.MerchantBusinessDetails;
import com.payfi.merchant.exception.ResourceMismatchException;
import com.payfi.merchant.exception.ResourceNotFoundException;


public interface MerchantBusinessDetailsService {
	
	//create a 
    public MerchantBusinessDetails  saveMerchantBusinessDetails(MerchantBusinessDetails merchantBusinessDetails);
    
    public Optional<MerchantBusinessDetails> findByMerchantOktaId(String merchantOktaId);

	public MerchantBusinessDetails registerMerchantBusinessDetails(String merchantOktaId,
			@Valid MerchantBusinessDetails merchantBusinessDetails) throws ResourceNotFoundException,ResourceMismatchException;

}
