package com.payfi.merchant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payfi.merchant.entity.MerchantDocumentDetails;
import com.payfi.merchant.entity.TableUser;

public interface TableUserRepository extends JpaRepository<TableUser, Long> {
	
	Optional<TableUser> findByMerchantOktaId(String merchantOktaId);

}
