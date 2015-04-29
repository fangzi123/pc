package com.micro.rent.pc.service;

import com.micro.rent.pc.entity.Address;

public interface AddressService {
	Address findAddressByBranchId(Long branchId) throws Exception;
	
	Long findAddressByAddress(String address) throws Exception;

 Long add(Address address) throws Exception;
}
