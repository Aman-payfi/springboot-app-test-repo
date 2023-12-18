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
@Table(name="tbl_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
//@Valid
public class TableUser{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
    private Long userId;
	
	//@NotEmpty(message="First name cannot be empty")
	@Column(name="first_name")
	private String firstName;
	
	//@NotEmpty(message="Last name cannot be empty")
	@Column(name="last_name")
	private String lastName;
	
	//@NotEmpty(message="Email cannot be empty")
	@Email(message="Please enter a valid email format")
    @Column(name = "email")
    private String email;
	
	@Column(name="username")
	private String username;
   
	//@NotEmpty(message="Password cannot be empty")
	@Column(name="password")
    private String password;
	
	//@NotEmpty(message="merchant Okta Id cannot be empty")
	@Column(name="merchant_okta_id")
	private String merchantOktaId;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
}
