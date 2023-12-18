package com.payfi.merchant.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payfi.merchant.entity.MerchantBasicDetails;
import com.payfi.merchant.entity.MerchantBusinessDetails;
import com.payfi.merchant.exception.ResourceMismatchException;
import com.payfi.merchant.exception.ResourceNotFoundException;
import com.payfi.merchant.repository.MerchantBusinessDetailsRepository;

@Service
public class MerchantBusinessDetailsServiceImpl implements MerchantBusinessDetailsService {
	
	@Autowired
	private MerchantBusinessDetailsRepository merchantBusinessDetailsRepository;

	@Override
	public MerchantBusinessDetails saveMerchantBusinessDetails(MerchantBusinessDetails merchantBusinessDetails) {
		return merchantBusinessDetailsRepository.save(merchantBusinessDetails);
	}

	@Override
	public MerchantBusinessDetails registerMerchantBusinessDetails(String merchantOktaId,
			@Valid MerchantBusinessDetails merchantBusinessDetails)
			throws ResourceNotFoundException, ResourceMismatchException {
		
		MerchantBusinessDetails existingMerchant = merchantBusinessDetailsRepository.findByMerchantOktaId(merchantOktaId).orElseThrow( () -> new ResourceNotFoundException("Merchant id incorrect, please enter correct merchant id "+merchantOktaId) );
	    if(!(existingMerchant.getMerchantOktaId()).equals(merchantOktaId)) {
	    	throw new ResourceMismatchException("User mismatch for merchant with id " + merchantOktaId);
	    }

	    MerchantBusinessDetails registerMerchantBusinessDetails = merchantBusinessDetailsRepository.save(existingMerchant);
		return registerMerchantBusinessDetails;
	}

	@Override
	public Optional<MerchantBusinessDetails> findByMerchantOktaId(String merchantOktaId) {
		return merchantBusinessDetailsRepository.findByMerchantOktaId(merchantOktaId);
	}

}
