package com.payfi.merchant.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_merchant_bank_details")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Valid
public class MerchantBankDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="merchant_bank_id")
	private Long merchantBankId;
	
	@Column(name="merchant_okta_id",unique = true)
	private String merchantOktaId;
	
	@Column(name="email", unique = true)
    private String email;
	
	@Column(name="beneficiary_name")
	private String beneficiaryName;
	
	@Column(name="account_number")
	private String accountNumber;
	
	@Column(name="branch_name")
	private String branchName;
	
	@Column(name="branch_ifsc_code")
	private String branchIFSCCode;
	
	@Column(name="created_at")
    private LocalDateTime createdAt;
}
