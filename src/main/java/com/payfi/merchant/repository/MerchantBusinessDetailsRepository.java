package com.payfi.merchant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payfi.merchant.entity.MerchantBusinessDetails;

public interface MerchantBusinessDetailsRepository extends JpaRepository<MerchantBusinessDetails, Long> {

	 Optional<MerchantBusinessDetails> findByMerchantOktaId(String merchantOktaId);
	
	

}
