package com.payfi.merchant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payfi.merchant.entity.MerchantBankDetails;
import com.payfi.merchant.entity.MerchantBasicDetails;

public interface MerchantBankDetailsRepository extends JpaRepository<MerchantBankDetails, Long> {
	
	Optional<MerchantBankDetails> findByMerchantOktaId(String merchantOktaId);

}
