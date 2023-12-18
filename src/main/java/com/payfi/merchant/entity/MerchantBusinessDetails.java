package com.payfi.merchant.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_merchant_business_details")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Valid
public class MerchantBusinessDetails {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "merchant_business_id")
    private Long merchantBusinessId;
    
    @Column(name="merchant_okta_id", unique = true)
	private String merchantOktaId;
    
    @Column(name = "company_name")
    private String companyName;
    
    @Column(name = "company_type")
    private String companyType;
    
    @Column(name = "gst_registration_number")
    private String gstNumber;
    
    @Column(name = "directors_id_number")
    private String directorsIdentificationNumber;
    
    //@Column(name = "partnership_deed")
    //private String partnershipDeed;
    
    @Column(name = "pan")
    private String companyPAN;
    
    //@Column(name="pan_holder_name")
    //private String panHolderName;
    
    //@Column(name="moa")
    //private String moa;
    
    @Column(name="cin")
    private String cin;
    
    //@Column(name = "aoa")
    //private String aoa;
    
    @Column(name="bank_account_number")
    private String bankAccountNumber;
    
    @Column(name = "company_website_link")
    private String companyWebsiteLink;
    
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Please enter a valid email format")
    @Column(name = "email_id",unique = true)
    private String emailId;
    
    @Column(name="business_contact")
    private String businessContact;
    
    //@Column(name="business_category")
    //private String business_category;
    
    //@Column(name="settlement_type")
    //private String settlementType;
    
    //@Column(name="perday_transaction_limit")
    //private String perdayTransactionLimit;
    
    @Column(name="created_at")
	private LocalDateTime createdAt;

	public MerchantBusinessDetails(String merchantOktaId, String companyName, String companyType, String gstNumber,
			String directorsIdentificationNumber, String companyPAN, String cin, String bankAccountNumber,
			String companyWebsiteLink,
			@NotEmpty(message = "Email cannot be empty") @Email(message = "Please enter a valid email format") String email,
			String businessContact) {
		super();
		this.merchantOktaId = merchantOktaId;
		this.companyName = companyName;
		this.companyType = companyType;
		this.gstNumber = gstNumber;
		this.directorsIdentificationNumber = directorsIdentificationNumber;
		this.companyPAN = companyPAN;
		this.cin = cin;
		this.bankAccountNumber = bankAccountNumber;
		this.companyWebsiteLink = companyWebsiteLink;
		this.emailId = email;
		this.businessContact = businessContact;
	}
    
    
    
}