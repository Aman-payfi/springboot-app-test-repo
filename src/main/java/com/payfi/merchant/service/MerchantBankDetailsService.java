package com.payfi.merchant.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.payfi.merchant.entity.MerchantBankDetails;
import com.payfi.merchant.entity.MerchantBasicDetails;

public interface MerchantBankDetailsService {

	//create a 
    public MerchantBankDetails  saveMerchantBankDetails(MerchantBankDetails merchantBankDetails);
    
    public Optional<MerchantBankDetails> findByMerchantOktaId(String merchantOktaId);

}
