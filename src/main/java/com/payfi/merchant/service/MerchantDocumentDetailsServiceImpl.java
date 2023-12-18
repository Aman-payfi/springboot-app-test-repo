package com.payfi.merchant.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payfi.merchant.entity.MerchantDocumentDetails;
import com.payfi.merchant.exception.ResourceMismatchException;
import com.payfi.merchant.exception.ResourceNotFoundException;
import com.payfi.merchant.repository.MerchantDocumentDetailsRepository;

@Service
public class MerchantDocumentDetailsServiceImpl implements MerchantDocumentDetailsService {
	
	@Autowired
	private MerchantDocumentDetailsRepository merchantDocumentDetailsRepository;

	@Override
	public MerchantDocumentDetails saveMerchantDocumentDetails(String merchantOktaId, MerchantDocumentDetails merchantDocumentDetails) {
		/*Optional<MerchantDocumentDetails> existingMerchantDocumentDetails = merchantDocumentDetailsRepository.findByMerchantOktaId(merchantOktaId);
		
		if(!existingMerchantDocumentDetails.isEmpty()) {
			merchantDocumentDetails = existingMerchantDocumentDetails.get();	
		}*/
		return merchantDocumentDetailsRepository.save(merchantDocumentDetails);
	}

	@Override
	public MerchantDocumentDetails registerMerchantDocumentsDetails(String merchantOktaId,
			MerchantDocumentDetails merchantDocumentDetails)
			throws ResourceNotFoundException, ResourceMismatchException {
		MerchantDocumentDetails existingMerchant = merchantDocumentDetailsRepository.findByMerchantOktaId(merchantOktaId).orElseThrow( () -> new ResourceNotFoundException("Merchant id incorrect, please enter correct merchant id "+merchantOktaId) );
	    if(!(existingMerchant.getMerchantOktaId()).equals(merchantOktaId)) {
	    	throw new ResourceMismatchException("User mismatch for merchant with id " + merchantOktaId);
	    }

	    MerchantDocumentDetails registerMerchantDocumentDetails = merchantDocumentDetailsRepository.save(existingMerchant);
		return registerMerchantDocumentDetails;
	}

	@Override
	public Optional<MerchantDocumentDetails> findByMerchantOktaId(String merchantOktaId) {
		return merchantDocumentDetailsRepository.findByMerchantOktaId(merchantOktaId);
	}
	
}
