package com.payfi.merchant.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.payfi.merchant.entity.MerchantBasicDetails;
import com.payfi.merchant.exception.ResourceMismatchException;
import com.payfi.merchant.exception.ResourceNotFoundException;


public interface MerchantBasicDetailsService {
	
	
    public MerchantBasicDetails  saveMerchantBasicDetails(MerchantBasicDetails merchantBasicDetails);
    
    public Optional<MerchantBasicDetails> findByMerchantOktaId(String merchantOktaId);

	public MerchantBasicDetails registerMerchantBasicDetails(String merchantOktaId,
			MerchantBasicDetails merchantBasicDetails) throws ResourceNotFoundException,ResourceMismatchException;

}
