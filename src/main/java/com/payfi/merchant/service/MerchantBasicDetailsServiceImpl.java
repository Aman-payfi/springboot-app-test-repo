package com.payfi.merchant.service;


import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.payfi.merchant.entity.MerchantBasicDetails;
import com.payfi.merchant.exception.ResourceMismatchException;
import com.payfi.merchant.exception.ResourceNotFoundException;
import com.payfi.merchant.externalservice.FeignTransactionService;
import com.payfi.merchant.repository.MerchantBasicDetailsRepository;

@Service
public class MerchantBasicDetailsServiceImpl implements MerchantBasicDetailsService {
	
	@Autowired
	private MerchantBasicDetailsRepository merchantBasicDetailsRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private FeignTransactionService feignTransactionService;
	
	//create/signup a Merchant
	@Override
	public MerchantBasicDetails saveMerchantBasicDetails(MerchantBasicDetails merchantBasicDetails) {
		/* if random merchant id string
		String randomMid = UUID.randomUUID().toString();
		merchant.setMid(randomMid);
		*/
		MerchantBasicDetails m = merchantBasicDetailsRepository.save(merchantBasicDetails);
		return m;
	}
	
	

	@Override
	public MerchantBasicDetails registerMerchantBasicDetails(String merchantOktaId,
			MerchantBasicDetails merchantBasicDetails) throws ResourceNotFoundException,ResourceMismatchException {
		
		MerchantBasicDetails existingMerchant = merchantBasicDetailsRepository.findByMerchantOktaId(merchantOktaId).orElseThrow( () -> new ResourceNotFoundException("Merchant id incorrect, please enter correct merchant id "+merchantOktaId) );
	    if(!(existingMerchant.getMerchantOktaId()).equals(merchantOktaId)) {
	    	throw new ResourceMismatchException("User mismatch for merchant with id " + merchantOktaId);
	    }

	    MerchantBasicDetails registerMerchantBasicDetails = merchantBasicDetailsRepository.save(existingMerchant);
		return registerMerchantBasicDetails;
	}



	@Override
	public Optional<MerchantBasicDetails> findByMerchantOktaId(String merchantOktaId) {
		return merchantBasicDetailsRepository.findByMerchantOktaId(merchantOktaId);
	}
	
	/*
	//register merchant
	public MerchantBusinessDetails registerMerchantDetails(String merchantOktaId, MerchantBusinessDetails merchantDetails) throws ResourceNotFoundException,ResourceMismatchException {
		
		MerchantBasicDetails existingMerchant = merchantBasicDetailsRepository.findByMerchantOktaId(merchantOktaId).orElseThrow( () -> new ResourceNotFoundException("Merchant id incorrect, please enter correct merchant id "+merchantOktaId) );
	    if(!(existingMerchant.getMerchantOktaId()).equals(merchantOktaId)) {
	    	throw new ResourceMismatchException("User mismatch for merchant with id " + merchantOktaId);
	    }
	   // merchantDetails.setCreatedAt(existingMerchant.getCreatedAt());
	   // merchantDetails.setStatus("verify Document");
	   // modelMapper.map(updatedMerchant, existingMerchant);
	    MerchantBusinessDetails registerMerchantDetails = merchantDetailsRepository.save(merchantDetails);
	    return registerMerchantDetails;
	}
	
	//get single Merchant with 	Mid
	@Override
	public Merchant getMerchantByMID(Long mid) throws ResourceNotFoundException {
		Merchant merchant = merchantRepository.findById(mid).orElseThrow( () -> new ResourceNotFoundException("Merchant id incorrect, please enter correct merchant id ") );
	    
		//api call to merchant transaction micro service
		//List<Transaction> transactionList = restTemplate.exchange("http://TRANSACTION/transactions/mid/"+merchant.getMid(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Transaction>>() {}).getBody();

		//set transactions of each merchants
		//merchant.setTransactions(transactionList);
		
		return merchant;
	}
	
	//get All Merchants
	@Override
	public List<Merchant> getAllMerchant(){
		return merchantRepository.findAll();
	}

	//update a merchant
	@Override
	public Merchant updateMerchant(Long mid, Merchant updatedMerchant){
		
		Optional<Merchant> merchant = merchantRepository.findById(mid);
		if(merchant.isEmpty()) {
			throw new ResourceNotFoundException("Merchant id incorrect, please enter correct merchant id ");
		}
		merchant.get().setStatus("pending");
		return merchantRepository.save(updatedMerchant);
	}
	
	//delete a Merchant
	@Override
	public void deleteMerchant(Merchant merchant) {
		merchant.setStatus("deleted");
        merchantRepository.save(merchant);		
	}

	public List<Merchant> getMerchantByAID(Long aid) {
		
		List<Merchant> merchantList = merchantRepository.findByAid(aid);
		
		merchantList = merchantList
				      .stream()
				      .map( merchant -> { merchant.setTransactions(  feignTransactionService.getTransactionByMID(merchant.getMid())  );
		                                                             return merchant; })
				      .collect(Collectors.toList());
		
		return merchantList;
	}
	*/
}
