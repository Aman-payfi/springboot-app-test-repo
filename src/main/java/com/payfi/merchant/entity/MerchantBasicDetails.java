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
@Table(name="tbl_merchant_basic_details")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Valid
public class MerchantBasicDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="merchant_id")
	private Long merchantId;
	
	@Column(name="merchant_okta_id", unique = true)
	private String merchantOktaId;
	
	@Column(name="email_Id",unique = true)
	private String emailId;
	
	@Column(name="merchant_name")
	private String merchantName;
	
	@Column(name="user_name",unique = true)
	private String username;
	
	@Column(name="profile_status")
	private String profileStatus;
	
	@Column(name="merchant_contact")
	private String merchantContact;
	
	@Column(name="designation")
	private String designation;
	
	@Column(name="address")
	private String address;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="country")
	private String country;
	
	@Column(name="postal_code")
	private String postalCode;
	
	@Column(name="account_currency")
	private String accountCurrency;
	
	@Column(name="ims_type")
	private String imstype;
	
	@Column(name="bims_type")
	private String bimstype;
	
	@Column(name="description")
	private String description;
	
	@Column(name="created_at")
    private LocalDateTime createdAt;

	public MerchantBasicDetails(String merchantOktaId, String emailId, String merchantName, String username,
			String profileStatus, String merchantContact, String designation, String address, String city, String state,
			String country, String postalCode, String accountCurrency, String iMSType, String bIMSType,
			String description) {
		super();
		this.merchantOktaId = merchantOktaId;
		this.emailId = emailId;
		this.merchantName = merchantName;
		this.username = username;
		this.profileStatus = profileStatus;
		this.merchantContact = merchantContact;
		this.designation = designation;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.postalCode = postalCode;
		this.accountCurrency = accountCurrency;
		this.imstype = iMSType;
		this.bimstype = bIMSType;
		this.description = description;
	}
	
	
	
}
