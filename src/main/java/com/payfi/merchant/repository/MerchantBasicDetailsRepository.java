package com.payfi.merchant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payfi.merchant.entity.MerchantBasicDetails;


public interface MerchantBasicDetailsRepository extends JpaRepository<MerchantBasicDetails, Long> {
	
	//List<MerchantBasicDetails> findByAid(Long aid);

	Optional<MerchantBasicDetails> findByMerchantOktaId(String merchantOktaId);
	
}
