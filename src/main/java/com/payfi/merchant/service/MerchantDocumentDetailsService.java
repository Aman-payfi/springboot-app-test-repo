package com.payfi.merchant.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.payfi.merchant.entity.MerchantBasicDetails;
import com.payfi.merchant.entity.MerchantDocumentDetails;
import com.payfi.merchant.exception.ResourceMismatchException;
import com.payfi.merchant.exception.ResourceNotFoundException;


public interface MerchantDocumentDetailsService {
	
    public MerchantDocumentDetails  saveMerchantDocumentDetails(String merchantOktaId,MerchantDocumentDetails merchantDocumentDetails);

    public Optional<MerchantDocumentDetails> findByMerchantOktaId(String merchantOktaId);
    
    public MerchantDocumentDetails registerMerchantDocumentsDetails(String merchantOktaId,
			MerchantDocumentDetails merchantDocumentDetails) throws ResourceNotFoundException,ResourceMismatchException;

}
