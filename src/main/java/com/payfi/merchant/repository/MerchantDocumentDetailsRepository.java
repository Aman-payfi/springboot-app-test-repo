package com.payfi.merchant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payfi.merchant.entity.MerchantDocumentDetails;

public interface MerchantDocumentDetailsRepository extends JpaRepository<MerchantDocumentDetails, Long> {

	Optional<MerchantDocumentDetails> findByMerchantOktaId(String merchantOktaId);

}
