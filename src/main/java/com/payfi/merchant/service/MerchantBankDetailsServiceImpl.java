package com.payfi.merchant.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payfi.merchant.entity.MerchantBankDetails;
import com.payfi.merchant.entity.MerchantBasicDetails;
import com.payfi.merchant.repository.MerchantBankDetailsRepository;

@Service
public class MerchantBankDetailsServiceImpl implements MerchantBankDetailsService {
	
	@Autowired
	private MerchantBankDetailsRepository merchantBankDetailsRepository;

	@Override
	public MerchantBankDetails saveMerchantBankDetails(MerchantBankDetails merchantBankDetails) {
		return merchantBankDetailsRepository.save(merchantBankDetails);
	}

	@Override
	public Optional<MerchantBankDetails> findByMerchantOktaId(String merchantOktaId) {
		return merchantBankDetailsRepository.findByMerchantOktaId(merchantOktaId);
	}

}
