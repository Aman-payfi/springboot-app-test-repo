package com.payfi.merchant.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payfi.merchant.entity.MerchantBankDetails;
import com.payfi.merchant.entity.MerchantBasicDetails;
import com.payfi.merchant.entity.MerchantBusinessDetails;
import com.payfi.merchant.entity.MerchantDocumentDetails;
import com.payfi.merchant.entity.TableUser;
import com.payfi.merchant.service.MerchantBankDetailsServiceImpl;
import com.payfi.merchant.service.MerchantBasicDetailsServiceImpl;
import com.payfi.merchant.service.MerchantBusinessDetailsServiceImpl;
import com.payfi.merchant.service.MerchantDocumentDetailsServiceImpl;
import com.payfi.merchant.service.TableUserServiceImpl;
import com.payfi.merchant.util.ExtractJWT;

@RestController
@RequestMapping("/merchants")
@Validated
public class MerchantController {
	
	@Autowired
	private TableUserServiceImpl tableUserService;
	
	@Autowired
	private MerchantBankDetailsServiceImpl merchantBankDetailsService;
	
	@Autowired
	private MerchantBusinessDetailsServiceImpl merchantBusinessDetailsService;
	
	@Autowired
	private MerchantBasicDetailsServiceImpl merchantBasicDetailsService;

	@Autowired
	private MerchantDocumentDetailsServiceImpl merchantDocumentsDetailsService;
	
    //@PostConstruct
	public void populateTestMerchant(){
		//Merchant merchant = new Merchant(1L,"Lalit","kumar","klalit57@gmail.com","password","temporaryOktaId","payfi","individual","123", "Aditya","NA","DQLPK7426L","NA","NA","NA","UnionBank",LocalDateTime.now(),"payfi.co.in","9728429718","Not Active",1L,null);
		//merchantService.saveMerchant(mer);
    	
    	TableUser tableUser = new TableUser();
    	MerchantBasicDetails merchantBasicDetails = new MerchantBasicDetails();
    	MerchantBusinessDetails merchantBusinessDetails = new MerchantBusinessDetails();
    	MerchantBankDetails merchantBankDetails = new MerchantBankDetails();
    	MerchantDocumentDetails merchantDocumentDetails = new MerchantDocumentDetails();
    	
    	tableUserService.saveUser(tableUser);
    	merchantBasicDetailsService.saveMerchantBasicDetails(merchantBasicDetails);
    	merchantBusinessDetailsService.saveMerchantBusinessDetails(merchantBusinessDetails);
    	merchantBankDetailsService.saveMerchantBankDetails(merchantBankDetails);
    	merchantDocumentsDetailsService.saveMerchantDocumentDetails("test",merchantDocumentDetails);
	}
    
	//sign up merchant
	@PostMapping("/signup")
	public ResponseEntity<TableUser> signupMerchant(@RequestBody TableUser tableUser){
		tableUser.setCreatedAt(LocalDateTime.now());
		tableUser.setUsername( tableUser.getEmail());
		TableUser createdUser = tableUserService.saveUser(tableUser);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	//registration of merchant basic and business details
	@PostMapping("/registermerchantdetails")
	public ResponseEntity<Map<String,Object>> registerMerchantDetails( @RequestHeader(value="Authorization") String token, 
			@RequestBody Map<String, Object> payload
			) throws IOException {	
		String merchantOktaId = ExtractJWT.payloadJWTExtraction(token, "\"uid\"");
		String merchnatEmailId = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
		
		Map<String,Object> res = new HashMap<String, Object>();
        if(merchantOktaId==null || merchnatEmailId==null) {
        	return ResponseEntity.status(HttpStatus.OK).body(res);
		}
        
        Optional<MerchantBasicDetails> m1 = merchantBasicDetailsService.findByMerchantOktaId(merchantOktaId);
        Optional<MerchantBusinessDetails> m2 = merchantBusinessDetailsService.findByMerchantOktaId(merchantOktaId);
        if(!m1.isEmpty() || !m2.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.OK).body(res);
        }
		
		Map<String, Object> merchantBasicDetailsMap = (Map<String, Object>) payload.get("merchantBasicDetails");
	    Map<String, Object> merchantBusinessDetailsMap = (Map<String, Object>) payload.get("merchantBusinessDetails");
	    
	    MerchantBasicDetails merchantBasicDetails = mapToMerchantBasicDetails(merchantBasicDetailsMap);
	    MerchantBusinessDetails merchantBusinessDetails = mapToMerchantBusinessDetails(merchantBusinessDetailsMap);
	    
		//setting creation time
		LocalDateTime localDateTime = LocalDateTime.now();
		merchantBasicDetails.setCreatedAt(localDateTime);
		merchantBasicDetails.setEmailId(merchnatEmailId);
		merchantBasicDetails.setMerchantOktaId(merchantOktaId);
		
		merchantBusinessDetails.setCreatedAt(localDateTime);
		merchantBusinessDetails.setEmailId(merchnatEmailId);
		merchantBusinessDetails.setMerchantOktaId(merchantOktaId);
		
		MerchantBasicDetails savedMerchantBasicDetails = merchantBasicDetailsService.saveMerchantBasicDetails(merchantBasicDetails);
		MerchantBusinessDetails savedMerchantBusinessDetails = merchantBusinessDetailsService.saveMerchantBusinessDetails(merchantBusinessDetails);
		
		res.put("merchantBasicDetails",savedMerchantBasicDetails );
		res.put("merchantBusinessDetails",savedMerchantBusinessDetails );
		
		return ResponseEntity.status(HttpStatus.OK).body(res);	

	}
	
	
	private MerchantBasicDetails mapToMerchantBasicDetails(Map<String, Object> map) {
	    ObjectMapper objectMapper = new ObjectMapper();
	    return objectMapper.convertValue(map, MerchantBasicDetails.class);
	}

	private MerchantBusinessDetails mapToMerchantBusinessDetails(Map<String, Object> map) {
	    ObjectMapper objectMapper = new ObjectMapper();
	    return objectMapper.convertValue(map, MerchantBusinessDetails.class);
	}
	
	/*
	//create a Merchant
	@PostMapping("/create/mid/{mid}")
	public ResponseEntity<Merchant> createMerchant(@PathVariable Long mid, @RequestBody Merchant merchant){
		//merchant.setStatus("pending");
		//merchant.setCreatedAt(LocalDateTime.now());
		Merchant createdMerchant = merchantService.saveMerchant(merchant);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMerchant);
	}
	
	//get Merchant with MID
	@GetMapping("/mid/{mid}")
	public ResponseEntity<Merchant> getMerchantByMID(@PathVariable Long mid){
		Merchant merchant = merchantService.getMerchantByMID(mid);	
		return ResponseEntity.ok(merchant);
	}
	
	//get all Merchants
	@GetMapping("/")
	public ResponseEntity<List<Merchant>> getAllMerchant(){
		List<Merchant> merchants = merchantService.getAllMerchant();
		return ResponseEntity.ok(merchants);
	}
	
	//Update Merchant with Mid
	@PutMapping("/update/{mid}")
	public ResponseEntity<Merchant> updateMerchantByMID(@PathVariable Long mid,@Valid @RequestBody Merchant updatedMerchant){
		    Merchant merchant = merchantService.updateMerchant(mid,updatedMerchant);
            return ResponseEntity.ok(merchant);
	}
	
	//delete Merchant with mid
	@DeleteMapping("/delete/{mid}")
    public ResponseEntity<String> deleteMerchant(@PathVariable Long mid){
		Merchant merchant = (Merchant) merchantService.getMerchantByMID(mid);
		merchantService.deleteMerchant(merchant);
		return ResponseEntity.ok("Merchant with ID " + mid + " has been deleted successfully.");
    }
	
	//get Merchant with AID
	@GetMapping("/aid/{aid}")
	public ResponseEntity<List<Merchant>> getMerchantByAID(@PathVariable Long aid){
		List<Merchant> merchantList = merchantService.getMerchantByAID(aid);
		return ResponseEntity.ok(merchantList);
	}
*/
	
	
    //registration of merchant documents
	@PostMapping("/registermerchantdetailsdocs")
	public ResponseEntity<MerchantDocumentDetails> registerMerchantDetails( 
			@RequestHeader(value="Authorization") String token,
			@RequestParam("partnerShipDeed") MultipartFile partnerShipDeed,
            @RequestParam("pancardDoc") MultipartFile pancardDoc,
            @RequestParam("moaDoc") MultipartFile moaDoc,
            @RequestParam("aoaDoc") MultipartFile aoaDoc,
            @RequestParam("bankCredentialsCheque") MultipartFile bankCredentialsCheque
			                                                           ) throws IOException {	
		String merchantOktaId = ExtractJWT.payloadJWTExtraction(token, "\"uid\"");
		String merchnatEmailId = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
		
		
		if(merchantOktaId==null || merchnatEmailId ==null ) {
        	return ResponseEntity.status(HttpStatus.OK).body(  new MerchantDocumentDetails() );
		}
		
		Optional<MerchantDocumentDetails> m1 = merchantDocumentsDetailsService.findByMerchantOktaId(merchantOktaId);
        if(!m1.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.OK).body(new MerchantDocumentDetails());
        }
		
		// Check if any of the byte arrays is empty
	    if (partnerShipDeed.isEmpty() || pancardDoc.isEmpty() || moaDoc.isEmpty() || aoaDoc.isEmpty() || bankCredentialsCheque.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MerchantDocumentDetails());
	    }
		
		MerchantDocumentDetails merchantDocumentDetails = new MerchantDocumentDetails(
				merchantOktaId,
				merchnatEmailId,
				partnerShipDeed.getBytes(),
				pancardDoc.getBytes(),
				moaDoc.getBytes(),
				aoaDoc.getBytes(),
				bankCredentialsCheque.getBytes()
				);
		
		//setting creation time
		LocalDateTime localDateTime = LocalDateTime.now();
		merchantDocumentDetails.setCreatedAt(localDateTime);
		
		MerchantDocumentDetails savedMerchantDocumentDetails = merchantDocumentsDetailsService.saveMerchantDocumentDetails(merchantOktaId,merchantDocumentDetails);
		
		return ResponseEntity.status(HttpStatus.OK).body( savedMerchantDocumentDetails );
	}
}
