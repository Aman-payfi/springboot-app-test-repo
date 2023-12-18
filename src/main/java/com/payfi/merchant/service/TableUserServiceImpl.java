package com.payfi.merchant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payfi.merchant.entity.TableUser;
import com.payfi.merchant.exception.ResourceNotFoundException;
import com.payfi.merchant.repository.TableUserRepository;

@Service
public class TableUserServiceImpl implements TableUserService {
	
	@Autowired
	private TableUserRepository tableUserRepository;

	@Override
	public TableUser saveUser(TableUser tableUser) {
		return tableUserRepository.save(tableUser);
	}

	
	@Override
	public TableUser getTableUserByMID(Long userId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TableUser> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableUser updateMerchant(Long mid, TableUser updatedTableUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMerchant(TableUser tblUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<TableUser> getTableUserByAID(Long aid) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Optional<TableUser> findByMerchantOktaId(String merchantOktaId) {
		return tableUserRepository.findByMerchantOktaId(merchantOktaId);
	}

}
